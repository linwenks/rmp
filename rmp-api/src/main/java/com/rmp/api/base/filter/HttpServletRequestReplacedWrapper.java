package com.rmp.api.base.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

/**
 * 封装 request
 * @author linw
 *
 */
public class HttpServletRequestReplacedWrapper extends HttpServletRequestWrapper {
	
	private DiskFileItemFactory factory = new DiskFileItemFactory();  
	private ServletFileUpload sfu = new ServletFileUpload(factory);
    
	// from param
    private Map<String, String[]> params = new HashMap<>();
    // body
	private final byte[] body;

    public HttpServletRequestReplacedWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = StreamUtils.copyToByteArray(request.getInputStream());
        setParams(new HttpServletRequestWrapper(request));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(), Charset.forName("UTF-8")));
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
    
    /**
     * 通过StreamingAPI的方式上传文件
     */
    private void setParams(HttpServletRequest request){
        //获取上传文件类型
        if (ServletFileUpload.isMultipartContent(request)){
            try {
            	Map<String, List<FileItem>> parameterMap = sfu.parseParameterMap(request);
            	if (CollectionUtils.isEmpty(parameterMap)) return;
            	
            	for (Map.Entry<String, List<FileItem>> entry : parameterMap.entrySet()) {
            		String name = entry.getKey();
            		List<FileItem> fileItemList = entry.getValue();
            		
            		if (CollectionUtils.isEmpty(fileItemList)) {
            			params.put(name, null);
            		} else {
            			for (FileItem fileItem : fileItemList) {
            				InputStream is = fileItem.getInputStream();
            				if (fileItem.isFormField()){//如果是非文件域,设置进入map,这里要注意多值处理
            					setFormParam(name, fileItem.getInputStream());
            				} else {
            					if (is.available() >0 ) {//如果输出流的内容大于0
                                    params.put(name,new String[]{fileItem.getFieldName()});//把文件名设置进request中
                                }
            				}
            			}
            		}
            	}
            	
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            params = request.getParameterMap();//如果不是文件上传请求,则直接设置map
        }
    }
    
    /**
     * 处理非上传的表单
     * @param name
     * @param is
     */
    private void setFormParam(String name, InputStream is) {
        try {
            if (params.containsKey(name)){//判断当前值name是否已经存储过
                String[] values = params.get(name);//取出已经存储过的值
                values = Arrays.copyOf(values,values.length+1);//把当前数组扩大
                values[values.length-1] = StreamUtils.copyToString(is, Charset.forName("utf-8"));//增加新值
                params.put(name,values);//重新添加到map中
            } else {
                params.put(name,new String[]{StreamUtils.copyToString(is, Charset.forName("utf-8"))});//直接存入参数中
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 返回参数map集合,自定义后上传文件,上传成功则返回文件名
     * @return
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }
 
    /**
     * 从请求中取出参数
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if(values!=null) {
            return values[0];
        }
        return null;
    }
 
    /**
     * 从请求中取出多个参数值,如checkbox的值
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if(values!=null) {
            return values;
        }
        return null;
    }
}