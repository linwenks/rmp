package com.rmp.api.base.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

import com.rmp.api.base.dao.redis.BaseShardedJedisPoolDao;
import com.rmp.api.base.spring.ApplicationContextUtil;


/**
 * 登录 拦截器
 * @author linw
 *
 */
public class ApiLoginFilter implements Filter {
	
	protected static Logger log = LoggerFactory.getLogger(ApiLoginFilter.class);
	
	private BaseShardedJedisPoolDao baseShardedJedisPoolDao;
//	private MemberService memberService;
	
	/**
	 * 需要排除的页面   
	 */
	private Set<String> excludedPageSet = new HashSet<>();
	private Set<String> excludedPageSet2 = new HashSet<>();
	
	@Override  
	public void init(FilterConfig fc) throws ServletException {
		
		String excludedPages = fc.getInitParameter("excludedPages");
		if (StringUtils.isNotEmpty(excludedPages)) {
			excludedPages = excludedPages.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "");
			String[] excludedPageArray = excludedPages.split(",");
			excludedPageSet.addAll(new HashSet<>(Arrays.asList(excludedPageArray)));
		}
		
		String excludedPages2 = fc.getInitParameter("excludedPages2");
		if (StringUtils.isNotEmpty(excludedPages2)) {
			excludedPages2 = excludedPages2.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "");
			String[] excludedPageArray = excludedPages2.split(",");
			excludedPageSet2.addAll(new HashSet<>(Arrays.asList(excludedPageArray)));
		} 
	}
	
	@Override  
	public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {
		
		if (baseShardedJedisPoolDao == null) {
			ApplicationContext applicationContext = ApplicationContextUtil.getContext();
			baseShardedJedisPoolDao = applicationContext.getBean(BaseShardedJedisPoolDao.class);
//			memberService = applicationContext.getBean(MemberService.class);
		}
		
		Boolean isJson = false;
		String contentType = request.getContentType();
		if (contentType != null
				&& (contentType.toLowerCase().indexOf("json") > 0 || contentType.toLowerCase().equals("text/plain"))) {
			isJson = true;
        }
		
		HttpServletRequest httpRequestWrapper = (HttpServletRequest) request;
		if (isJson) {
			ServletRequest requestWrapper = new HttpServletRequestReplacedWrapper(httpRequestWrapper);
			httpRequestWrapper = (HttpServletRequest) requestWrapper;
		}
		
		String url = httpRequestWrapper.getServletPath();
        
        // 判断是否在过滤url之外     
		boolean isExcludedPage = false;
		for (String excludedPage : excludedPageSet) {
			if (excludedPage.startsWith(url)) {
				isExcludedPage = true;
				break;
			}
		}
		if (!StringUtils.isEmpty(url)) {
			for (String excludedPage : excludedPageSet2) {
				if (url.startsWith(excludedPage)) {
					isExcludedPage = true;
					break;
				}
			}
		}
		
//		if (excludedPageSet.contains(url)) isExcludedPage = true;
        
		if (!isExcludedPage) {
			/*
			try {
				
				ApiReqJsonBean apiReqJsonBean = null;
				
				if (isJson) {

					// 不在过滤url之外，判断token是否存在     
					String body = IOUtils.toString(httpRequestWrapper.getReader());
					body = StringUtils.trim(body);
					if (StringUtils.isEmpty(body)) throw new AppException(Constant.Msg.Api._00003);
					
					try {
						apiReqJsonBean = JsonUtil.fromJson(body, ApiReqJsonBean.class);
					} catch (Exception e) {
						log.error(e.getMessage(), e);
						throw new AppException(Constant.Msg.Api._00005);
					}
					
					HeaderJsonBean headerJsonBean = apiReqJsonBean.getHeader();
					if (headerJsonBean == null) throw new AppException(Constant.Msg.Api._00006);
					String token = headerJsonBean.getToken();
					token = StringUtils.trim(token);
					if (StringUtils.isEmpty(token)) throw new AppException(Constant.Msg.Api._00006);
					
					
					// redis 查询
					int index = Constant.Redis.Member.INDEX;
					
					Long memberId = null;
					String key = MemberUtil.key(token);
					Map<String, String> member2Map = baseShardedJedisPoolDao.hgetAll(index, key);
					if (member2Map == null || member2Map.isEmpty()) {
						
						// mysql 查询
						MemberBean member2 = new MemberBean();
						member2.setToken(token);
						Member member = memberService.selectOne(member2);
						if (member == null) throw new AppException(Constant.Msg.Api._00007);
						memberId = member.getId();
						
						String readTimeJson = null;
						MemberCategoryBean memberCategoryBean = new MemberCategoryBean();
						memberCategoryBean.setMemberId(memberId);
						MemberCategory memberCategory = memberCategoryService.selectOne(memberCategoryBean);
						if (memberCategory != null) {
							readTimeJson = memberCategory.getReadTimeJson();
						}
						
						MemberBean member2Tmp = new MemberBean();
						BeanUtils.copyProperties(member, member2Tmp);
						//member2Tmp.setReadTimeJson(readTimeJson);
						member2Map = MemberUtil.get(member2Tmp);
						
						baseShardedJedisPoolDao.hmset(index, key, member2Map);
						
						Date nowDate = DateUtil.now();
						Integer nowDateInt = Integer.valueOf(DateUtil.formatDate(nowDate, DateUtil.YYYYMMDD));
						Long nowDateLong = DateUtil.nowLong2(nowDate);
						
						member2 = new MemberBean();
						BeanUtils.copyProperties(member, member2);
						member2.setLoginTime(nowDateLong);
						member2.setUpdateTime(nowDateLong);
						//memberService.exe(BaseService.UPDATE_PK_SEl_VER, member2);
						memberService.exe(BaseService.UPDATE_PK_SEl, member2);
						
						// 添加 日志
						try {
							MemberLoginLogBean memberLoginLogBean = new MemberLoginLogBean();
							memberLoginLogBean.setAddDate(nowDateInt);
							memberLoginLogBean.setType(Constant.MemberLoginLog.Type._2);
							memberLoginLogBean.setMemberId(memberId);
							memberLoginLogBean.setIp(IpUtil.getIpAddress(httpRequestWrapper));
							memberLoginLogBean.setCreateTime(nowDateLong);
							memberLoginLogService.exe(BaseService.INSERT_SEL, memberLoginLogBean);
						} catch (Exception e) {
							log.error(e.getMessage(), e);
						}
						
						// 用户 喜好
						MemberLikeUtil.reload(memberId);
					}
					
					// 重新设置过期时间
					baseShardedJedisPoolDao.expire(index, key, Constant.Redis.Member.KEY_SECONDS);
					MemberLikeUtil.resetTtl(memberId);
		
					// 用于返回
					httpRequestWrapper.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerJsonBean);
					httpRequestWrapper.setAttribute(Constant.CURRENT_LOGIN_USER, MemberUtil.get(member2Map));
					
				} else {
					
					String token = httpRequestWrapper.getParameter("token");
					
					apiReqJsonBean = new ApiReqJsonBean();
					
					HeaderJsonBean headerJsonBean = new HeaderJsonBean();
					headerJsonBean.setToken(token);
					
					apiReqJsonBean.setHeader(headerJsonBean);
					
				}
			} catch (Exception e) {
				List<MsgBean> msgList = new ArrayList<>();
				if (e instanceof AppException) {
					AppException appException = (AppException) e;
					msgList = appException.getMsgList();
				}
				if (msgList.isEmpty()) {
					msgList.add(new MsgBean(Constant.Msg.Api._00002));
				}
				
				RespJsonBean respJsonBean = new RespJsonBean();
				respJsonBean.setMsgList(msgList);
				respJsonBean.setMsg(msgList.get(0));
				respJsonBean.setState(Constant.Msg.ERROR);
				response.setContentType("application/json");
				response.getWriter().write(JsonUtil.toJson(respJsonBean));
				return;
			}
			*/
		}     
        
        chain.doFilter(httpRequestWrapper, response);    
    }  
    
	@Override  
	public void destroy() {  
	      
	}
}