package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.ImmutableMap;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.mapper.CustomerMapperCustom;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.model.CustomerMaintainBean;
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.service.customer.CustomerDetailService;
import com.rmp.api.service.customer.CustomerFamilyService;
import com.rmp.api.service.customer.CustomerHobbyService;
import com.rmp.api.service.customer.CustomerJobService;
import com.rmp.api.service.customer.CustomerMaintainService;
import com.rmp.api.service.customer.CustomerMemorialDayService;
import com.rmp.api.service.customer.CustomerProblemService;
import com.rmp.api.service.customer.CustomerRelationService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.service.user.UserRemindService;
import com.rmp.api.util.CustomerUtil;
import com.rmp.api.util.HeadPicUtil;
import com.rmp.api.util.UserRemindUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.page.QueryPage;
import com.rmp.common.util.DateUtil;
import com.rmp.common.util.PinYinUtil;
import com.rmp.info.mapper.CustomerMapper;
import com.rmp.info.model.Customer;
import com.rmp.info.model.CustomerCriteria;

/**
 * 客户 service impl
 * @author linw
 *
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, CustomerBean, CustomerCriteria> implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerMapperCustom customerMapperCustom;
	
	@Autowired
	private CustomerFamilyService customerFamilyService;
	@Autowired
	private CustomerHobbyService customerHobbyService;
	@Autowired
	private CustomerJobService customerJobService;
	@Autowired
	private CustomerMaintainService customerMaintainService;
	@Autowired
	private CustomerMemorialDayService customerMemorialDayService;
	@Autowired
	private CustomerProblemService customerProblemService;
	@Autowired
	private CustomerRelationService customerRelationService;
	@Autowired
	private CustomerDetailService customerDetailService;
	@Autowired
	private UserRemindService userRemindService;
	
	@Override
	public CustomerMapper mapper() {
		return customerMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "save": save((CustomerBean) obj);break;
			case "saveAll": saveAll((CustomerBean) obj);break;
			case "update": update((CustomerBean) obj);break;
			case "updateAll": updateAll((CustomerBean) obj);break;
			case "updateHeadPic": updateHeadPic((CustomerBean) obj);break;
			case "delete": deleteCustom((CustomerBean) obj);break;
			default: return super.exe(cmd, obj);
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			AppException.toThrow(e);
		}
		return null;
	}
	
	@Override
	protected void where(Object criteria, CustomerBean bean) {
		if (bean == null) {
			return;
		}
		CustomerCriteria.Criteria criteriaTmp = (CustomerCriteria.Criteria) criteria;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (bean.getIdNot() != null) {
			criteriaTmp.andIdNotEqualTo(bean.getIdNot());
		}
		if (bean.getUserId() != null) {
			criteriaTmp.andUserIdEqualTo(bean.getUserId());
		}
		if (bean.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(bean.getIsDelete());
		}
		if (bean.getPhone() != null) {
			criteriaTmp.andPhoneEqualTo(bean.getPhone());
		}
	}
	
	public List<CustomerBean> selectListCustom(QueryPage queryPage, CustomerBean customerBean) {
		return customerMapperCustom.selectList(queryPage, customerBean);
	}
	
	
	/**
	 * 添加
	 * 姓名、手机号、头像
	 * @param customerBean
	 * @throws IOException 
	 */
	private void save(CustomerBean customerBean) throws IOException {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerBean.getUserId();
		String realName = StringUtils.trim(customerBean.getRealName());
		Long phone = customerBean.getPhone();
		String headPic = StringUtils.trim(customerBean.getHeadPic());
		
		if (userId == null) AppException.toThrow(MSG_00003);
		CustomerUtil.checkRealName(realName);
		CustomerUtil.checkPhone(phone);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().userId(userId).phone(phone).build();
		List<CustomerBean> customerBeanTmpList = selectList(null, customerBeanTmp);
		if (!CollectionUtils.isEmpty(customerBeanTmpList)) AppException.toThrow(MSG_02004);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);

		boolean isMove = false;
		String headPicNew = Constant.Customer.HEAD_PIC;
		String headPicOld = null;
		if (!StringUtils.isEmpty(headPic)) {
			Map<String, Object> headPicMap = HeadPicUtil.getHeadPic(headPic);
			isMove = (boolean) headPicMap.get("isMove");
			headPicNew = (String) headPicMap.get("headPicNew");
			headPicOld = (String) headPicMap.get("headPicOld");
		}
		
		// 添加
		customerBeanTmp = CustomerBean.builder()
		.userId(userId)
		.realName(realName)
		.pinyin(PinYinUtil.toPinYinLast(realName))
		.phone(phone)
		.headPic(headPicNew)
		.createTime(nowDateLong)
		.build();
		insertSel(customerBeanTmp);

		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				userRemindService.exe("insertBy3", UserRemindBean.builder().userId(userId).typeId(customerBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
		BeanUtils.copyProperties(customerBeanTmp, customerBean);
		
		// 移动文件
		if (isMove) {
			HeadPicUtil.moveHeadPic(headPicNew, headPicOld);
		}
	}
	
	/**
	 * 添加 全部
	 * @param customerBean
	 * @throws IOException 
	 */
	private void saveAll(CustomerBean customerBean) throws IOException {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerBean.getUserId();
		String realName = StringUtils.trim(customerBean.getRealName());
		Long phone = customerBean.getPhone();
		
		Integer birthday = customerBean.getBirthday();
		Integer sex = customerBean.getSex();
		String area = StringUtils.trim(customerBean.getArea());
		String address = StringUtils.trim(customerBean.getAddress());
		String headPic = StringUtils.trim(customerBean.getHeadPic());
		
		if (userId == null) AppException.toThrow(MSG_00003);
		CustomerUtil.checkRealName(realName);
		CustomerUtil.checkPhone(phone);
		CustomerUtil.checkAddress(address);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().userId(userId).phone(phone).build();
		List<CustomerBean> customerBeanTmpList = selectList(null, customerBeanTmp);
		if (!CollectionUtils.isEmpty(customerBeanTmpList)) AppException.toThrow(MSG_02004);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		boolean isMove = false;
		String headPicNew = Constant.Customer.HEAD_PIC;
		String headPicOld = null;
		if (!StringUtils.isEmpty(headPic)) {
			Map<String, Object> headPicMap = HeadPicUtil.getHeadPic(headPic);
			isMove = (boolean) headPicMap.get("isMove");
			headPicNew = (String) headPicMap.get("headPicNew");
			headPicOld = (String) headPicMap.get("headPicOld");
		}
		
		// 添加
		customerBeanTmp = CustomerBean.builder()
		.userId(userId)
		.realName(realName)
		.pinyin(PinYinUtil.toPinYinLast(realName))
		.phone(phone)
		.headPic(headPicNew)
		.birthday(birthday)
		.sex(sex)
		.area(area)
		.address(address)
		.createTime(nowDateLong)
		.build();
		insertSel(customerBeanTmp);
		
		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				userRemindService.exe("insertBy3", UserRemindBean.builder().userId(userId).typeId(customerBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
		BeanUtils.copyProperties(customerBeanTmp, customerBean);
		
		// 移动文件
		if (isMove) {
			HeadPicUtil.moveHeadPic(headPicNew, headPicOld);
		}
	}
	
	/**
	 * 修改
	 * 姓名、手机号、头像
	 * @param customerBean
	 * @throws IOException 
	 */
	private void update(CustomerBean customerBean) throws IOException {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long id = customerBean.getId();
		Long userId = customerBean.getUserId();
		String realName = StringUtils.trim(customerBean.getRealName());
		Long phone = customerBean.getPhone();
		String headPic = StringUtils.trim(customerBean.getHeadPic());
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		CustomerUtil.checkRealName(realName);
		CustomerUtil.checkPhone(phone);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(id).build();
		customerBeanTmp = selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp2 = CustomerBean.builder().idNot(id).userId(userId).phone(phone).build();
		List<CustomerBean> customerBeanTmp2List = selectList(null, customerBeanTmp2);
		if (!CollectionUtils.isEmpty(customerBeanTmp2List)) AppException.toThrow(MSG_02004);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		boolean isMove = false;
		String headPicNew = customerBeanTmp.getHeadPic();
		String headPicOld = null;
		if (!StringUtils.isEmpty(headPic)) {
			Map<String, Object> headPicMap = HeadPicUtil.getHeadPic(headPic);
			isMove = (boolean) headPicMap.get("isMove");
			headPicNew = (String) headPicMap.get("headPicNew");
			headPicOld = (String) headPicMap.get("headPicOld");
		}
		
		// 修改
		customerBeanTmp.setRealName(realName);
		customerBeanTmp.setPinyin(PinYinUtil.toPinYinLast(realName));
		customerBeanTmp.setPhone(phone);
		customerBeanTmp.setHeadPic(headPicNew);
		customerBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerBeanTmp);

		// 删除 提醒
		userRemindService.exe(DELETE_NOT_E, UserRemindBean.builder().userId(userId).type(3).typeId(customerBeanTmp.getId()).build());
		
		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				userRemindService.exe("insertBy3", UserRemindBean.builder().userId(userId).typeId(customerBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
		BeanUtils.copyProperties(customerBeanTmp, customerBean);
		
		// 移动文件
		if (isMove) {
			HeadPicUtil.moveHeadPic(headPicNew, headPicOld);
		}
	}
	
	/**
	 * 修改 全部
	 * @param customerBean
	 * @throws IOException 
	 */
	private void updateAll(CustomerBean customerBean) throws IOException {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long id = customerBean.getId();
		Long userId = customerBean.getUserId();
		String realName = StringUtils.trim(customerBean.getRealName());
		Long phone = customerBean.getPhone();

		Integer birthday = customerBean.getBirthday();
		Integer sex = customerBean.getSex();
		String area = StringUtils.trim(customerBean.getArea());
		String address = StringUtils.trim(customerBean.getAddress());
		String headPic = StringUtils.trim(customerBean.getHeadPic());
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		CustomerUtil.checkRealName(realName);
		CustomerUtil.checkPhone(phone);
		CustomerUtil.checkAddress(address);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(id).build();
		customerBeanTmp = selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp2 = CustomerBean.builder().idNot(id).userId(userId).phone(phone).build();
		List<CustomerBean> customerBeanTmp2List = selectList(null, customerBeanTmp2);
		if (!CollectionUtils.isEmpty(customerBeanTmp2List)) AppException.toThrow(MSG_02004);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		boolean isMove = false;
		String headPicNew = customerBeanTmp.getHeadPic();
		String headPicOld = null;
		if (!StringUtils.isEmpty(headPic)) {
			Map<String, Object> headPicMap = HeadPicUtil.getHeadPic(headPic);
			isMove = (boolean) headPicMap.get("isMove");
			headPicNew = (String) headPicMap.get("headPicNew");
			headPicOld = (String) headPicMap.get("headPicOld");
		}
		
		// 修改
		customerBeanTmp.setRealName(realName);
		customerBeanTmp.setPinyin(PinYinUtil.toPinYinLast(realName));
		customerBeanTmp.setPhone(phone);
		customerBeanTmp.setHeadPic(headPicNew);
		customerBeanTmp.setBirthday(birthday);
		customerBeanTmp.setSex(sex);
		customerBeanTmp.setArea(area);
		customerBeanTmp.setAddress(address);
		customerBeanTmp.setUpdateTime(nowDateLong);
		updatePkVer(customerBeanTmp);
		
		// 删除 提醒
		userRemindService.exe(DELETE_NOT_E, UserRemindBean.builder().userId(userId).type(3).typeId(customerBeanTmp.getId()).build());
		
		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				userRemindService.exe("insertBy3", UserRemindBean.builder().userId(userId).typeId(customerBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
		BeanUtils.copyProperties(customerBeanTmp, customerBean);
		
		// 移动文件
		if (isMove) {
			HeadPicUtil.moveHeadPic(headPicNew, headPicOld);
		}
	}
	
	/**
	 * 删除
	 * @param customerBean
	 */
	private void deleteCustom(CustomerBean customerBean) {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long id = customerBean.getId();
		Long userId = customerBean.getUserId();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(id).userId(userId).build();
		customerBeanTmp = selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		// 修改
		customerBeanTmp.setIsDelete(Constant.DELETE_Y);
		customerBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerBeanTmp);
		
		// 删除全部
		customerFamilyService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerFamilyBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerFamilyBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		customerHobbyService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerHobbyBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerHobbyBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		customerJobService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerJobBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerJobBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		customerMaintainService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerMaintainBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerMaintainBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		customerMemorialDayService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerMemorialDayBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerMemorialDayBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		customerProblemService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerProblemBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerProblemBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		customerRelationService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerProblemBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerProblemBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		customerDetailService.exe(UPDATE_SEl_NOT_E, ImmutableMap.of(
				"obj", CustomerProblemBean.builder().isDelete(Constant.DELETE_Y).updateTime(nowDateLong).build()
				, "objEx", CustomerProblemBean.builder().isDelete(Constant.DELETE_N).customerId(id).build()));
		
		// 删除 提醒
		userRemindService.exe(DELETE_NOT_E, UserRemindBean.builder().userId(userId).type(3).typeId(customerBeanTmp.getId()).build());
	}
	

	/**
	 * 修改 头像
	 * @param customerBean
	 * @throws IOException 
	 * @throws Exception 
	 */
	private void updateHeadPic(CustomerBean customerBean) throws IOException {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long id = customerBean.getId();
		Long userId = customerBean.getUserId();
		String headPic = StringUtils.trim(customerBean.getHeadPic());
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		if (StringUtils.isEmpty(headPic)) AppException.toThrow(MSG_00003);
		
		Map<String, Object> headPicMap = HeadPicUtil.getHeadPic(headPic);
		boolean isMove = (boolean) headPicMap.get("isMove");
		String headPicNew = (String) headPicMap.get("headPicNew");
		String headPicOld = (String) headPicMap.get("headPicOld");
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(id).userId(userId).build();
		customerBeanTmp = selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		customerBeanTmp.setHeadPic(headPicNew);
		customerBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerBeanTmp);
		
		// redis
		BeanUtils.copyProperties(customerBeanTmp, customerBean);
		
		// 移动文件
		if (isMove) {
			HeadPicUtil.moveHeadPic(headPicNew, headPicOld);
		}
	}
}