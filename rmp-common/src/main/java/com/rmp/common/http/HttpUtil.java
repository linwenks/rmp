package com.rmp.common.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 封装了采用HttpClient发送HTTP请求的方法
 * 
 * @author linw
 */
public class HttpUtil {
	
	protected static Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
    private Integer socketTimeout;    // 请求
    private Integer connectTimeout;    // 连接
    
    private String resultEncode = "utf-8";
    
	public HttpUtil() {}
	
	public CloseableHttpClient build() {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(buildRequestConfig())
				.build();
		return httpClient;
	}
	
    /**
   	 * 构建 request config
   	 * @author linw
   	 * @return
   	 */
   	private RequestConfig buildRequestConfig() {
   		RequestConfig.Builder builder = RequestConfig.custom();
   		if (socketTimeout != null) {
   			builder.setSocketTimeout(socketTimeout);    // 请求
   		}
   		if (socketTimeout != null) {
   			builder.setConnectTimeout(connectTimeout);    // 连接
   		}
   		return builder.build();
   	}
   	
   	private Map<String, Object> resultMap(HttpResponse httpResponse) throws ParseException, IOException {
   		HttpEntity httpEntity = httpResponse.getEntity();
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		Charset respCharset = ContentType.getOrDefault(httpEntity).getCharset(); // 获取响应码
		if (respCharset == null) {
			if (resultEncode != null) {
				respCharset = Charset.forName(resultEncode);
			}
		}
		String responseContent = EntityUtils.toString(httpEntity, respCharset); 
		
		Map<String, Object> map = new HashMap<>();
		map.put("statusCode", statusCode);
		map.put("responseContent", responseContent);
		return map;
   	}
	       
    /**
     * 发送HTTP_GET请求
     * @author	schoff
     * @param url 		请求链接，包含条件
     * @return Map<String, Object> 	远程主机响应正文[respContent]和session[cookie]
     */
    public Map<String, Object> sendGetRequest(String url){
    	Map<String, Object> map = new HashMap<String, Object>();
        HttpGet httpGet = new HttpGet(url); 
        try (CloseableHttpClient httpClient = build()) {
            HttpResponse response = httpClient.execute(httpGet); //执行GET请求
            map = resultMap(response);
        } catch (Exception e){ 
            log.error(" sendGetRequest [" + url + "] error " + e.getMessage() , e); 
        }
        return map; 
    } 
       
       
    /**
     * 发送HTTP_POST请求
     * @author	schoff
     * @param url			请求地址
     * @param data       	请求参数,若有多个参数则应拼接为param 11=value11&22=value22&33=value33的形式
     * @param encodeCharset 编码字符集,编码请求数据时用,此参数为必填项(不能为""或null)
     * @return Map<String, Object>		远程主机响应正文[respContent]和session[cookie]
     */
    public Map<String, Object> sendPostRequest(String url, String data, String encodeCharset){
    	Map<String, Object> map = new HashMap<String, Object>();
        HttpPost httpPost = new HttpPost(url); 
        //手工指定CONTENT_TYPE头消息，编码
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + encodeCharset);
		try (CloseableHttpClient httpClient = build()) {
            httpPost.setEntity(new StringEntity(data == null ? "" : data, encodeCharset)); 
            HttpResponse response = httpClient.execute(httpPost);
            map = resultMap(response);
        } catch(Exception e){ 
        	log.error(" sendPostRequest [" + url + "] error " + e.getMessage() , e); 
        } 
        return map; 
    }
       
    /**
     * 发送HTTP_ENTITY_POST请求
     * @author	schoff
     * @param url			请求地址
     * @param params       	请求参数 Map<String,String>
     * @param encodeCharset 编码字符集,编码请求数据时用,此参数为必填项(不能为""或null)
     * @return Map<String, Object>		远程主机响应正文[respContent]和session[cookie]
     */
    public Map<String, Object> sendPostMapRequest(String url,Map<String, String> params, String encodeCharset){
    	return sendPostMapRequest(url, null, params, encodeCharset);
    }
    
    /**
     * 发送HTTP_ENTITY_POST请求
     * @author	schoff
     * @param url			请求地址
     * @param headers       请求头参数 Map<String,String>
     * @param params       	请求参数 Map<String,String>
     * @param encodeCharset 编码字符集,编码请求数据时用,此参数为必填项(不能为""或null)
     * @return Map<String, Object>		远程主机响应正文[respContent]和session[cookie]
     */
    public Map<String, Object> sendPostMapRequest(String url, Map<String, String> headers, Map<String, String> params, String encodeCharset) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	HttpPost httpPost = new HttpPost(url); 
    	//手工指定CONTENT_TYPE头消息，编码
    	httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + encodeCharset);
    	//构建POST请求的表单参数
        if (null != headers){
            for(Map.Entry<String,String> entry : headers.entrySet()){
            	httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
    	List<NameValuePair> formParams = new ArrayList<NameValuePair>();
    	//构建POST请求的表单参数
        if (null != params){
            for(Map.Entry<String,String> entry : params.entrySet()){
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
		try (CloseableHttpClient httpClient = build()) {
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset));
            HttpResponse response = httpClient.execute(httpPost);
            map = resultMap(response);
        } catch(Exception e){ 
        	log.error(" sendPostMapRequest [" + url + "] error " + e.getMessage() , e);
        }
        return map; 
    }
       
    /**
     * 发送HTTP_POST_SSL请求
     * @param url			请求地址
     * @param params        请求参数
     * @param encodeCharset 编码字符集,编码请求数据时用之,当其为null时,则取HttpClient内部默认的ISO-8859-1编码请求参数
     * @return Map<String, Object>		远程主机响应正文[respContent]和session[cookie]
     */ 
    public Map<String, Object> sendPostSSLRequest(String url, Map<String, String> params, String encodeCharset){
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
        //解决javax.net.ssl.SSLPeerUnverifiedException
        X509TrustManager trustManager = new X509TrustManager(){
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            @Override
            public X509Certificate[] getAcceptedIssuers() {return null;}
        };
        
        //用于解决javax.net.ssl.SSLException
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier(){
            @Override
            public void verify(String host, SSLSocket ssl) throws IOException {}
            @Override
            public void verify(String host, X509Certificate cert) throws SSLException {}
            @Override
            public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {}
            @Override
            public boolean verify(String arg0, SSLSession arg1) {return true;}
        };
        
        try (CloseableHttpClient httpClient = build()) {
            SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS);
            //初始化该上下文
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext, hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
            //创建HttpPost
            HttpPost httpPost = new HttpPost(url);
            //构建POST请求的表单参数
            if(null != params){
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for(Map.Entry<String,String> entry : params.entrySet()){
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset));
            }
            
            HttpResponse response = httpClient.execute(httpPost);
            map = resultMap(response);
        } catch (Exception e) {
        	log.error(" sendPostMapRequest [" + url + "] error " + e.getMessage() , e);
        }
        return map;
    }
	
    /**
     * 上传文件
     * @author	schoff
     * @param	url				请求地址
     * @param	file			文件File
     * @param	fileName		对方需要的file名字
     * @param	encodeCharset	编码
     * @return String			对方返回的消息
     */
    public Map<String, Object> uploadFile(String url, File file ,String fileName, String encodeCharset) {
    	Map<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		MultipartEntity entity = new MultipartEntity();
		/**
		  * params 参数
		  */
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> mapEntry : params.entrySet()) {
				try {
					StringBody stringBody = new StringBody(mapEntry.getValue().toString());
					entity.addPart(mapEntry.getKey(), stringBody);
				} catch (UnsupportedEncodingException e) {
					log.error("获取params中[" + mapEntry.getKey() + "]值错误");
				}
			}
		}
		
		/**
		 * 文件
		 */
		if (file.exists()) {
			FileBody fileBody = new FileBody(file);
			entity.addPart(fileName, fileBody);
		}
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		try (CloseableHttpClient httpClient = build()) {
			HttpResponse response = httpClient.execute(httpPost);
			map = resultMap(response);
		} catch (IOException e) {
			log.error(" sendPostMapRequest [" + url + "] error " + e.getMessage() , e);
		}
    	return map;
	}
    
    /**
     * 下载 抛异常
     * @param url
     * @param filePath
     * @return
     * @throws Exception 
     */
    public Map<String, Object> download(String url, String filePath) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        HttpGet httpGet = new HttpGet(url); 
        try (CloseableHttpClient httpClient = build()) {
            HttpResponse httpResponse = httpClient.execute(httpGet); //执行GET请求
            
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            map.put("statusCode", statusCode);
            
            if (Integer.valueOf(200).equals(statusCode)) {
            	HttpEntity httpEntity = httpResponse.getEntity();
         		
         		byte[] responseContent = EntityUtils.toByteArray(httpEntity);
         		if (responseContent != null && responseContent.length > 0) {
         			File file = new File(filePath);
             		file.getParentFile().mkdirs();
             		file.delete();
             		
             		try (FileOutputStream fos = new FileOutputStream(file)) {
             			fos.write(responseContent);
             		} catch (Exception e) {
                        log.error(" download [" + url + "] error " + e.getMessage() , e);
                        throw e;
    				}
         		}
            }
        } catch (Exception e){ 
        	log.error(" download [" + url + "] error " + e.getMessage() , e);
            throw e;
        }
        return map; 
    }
    
    /**
     * 上传
     * 
     * @param file 上传的文件
     * @return 响应结果
     */
    public Map<String, Object> uploadFile(String url, List<HttpParamBean> httpParamBeanList, String encodeCharset) {
    	Map<String, Object> map = new HashMap<String, Object>();
        HttpPost httpPost = new HttpPost(url);
		try (CloseableHttpClient httpClient = build()) {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            if (httpParamBeanList != null) {
            	for (HttpParamBean httpParamBean : httpParamBeanList) {
            		String name = httpParamBean.getName();
            		Object value = httpParamBean.getValue();
            		ContentType contentType = httpParamBean.getContentType();
            		String filename = httpParamBean.getFilename();
            		if (value != null) {
            			if (value instanceof File) {
            				builder.addBinaryBody(name, (File) value, contentType, filename);	// 文件
            			} else if (value instanceof InputStream) {
            				builder.addBinaryBody(name, (InputStream) value, contentType, filename);	// 流
            			} else {
            				builder.addTextBody(name, value.toString());	// 类似浏览器表单提交，对应input的name和value
            			}
            		}
            	}
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            
            HttpResponse response = httpClient.execute(httpPost);
            map = resultMap(response);
        } catch(Exception e){ 
        	log.error(" uploadFile [" + url + "] error " + e.getMessage() , e); 
        } 
        return map;
    }

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	
	public String getResultEncode() {
		return resultEncode;
	}

	public void setResultEncode(String resultEncode) {
		this.resultEncode = resultEncode;
	}
	

	public static void main(String[] args) {
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.setConnectTimeout(10000);
		httpUtil.setSocketTimeout(10000);
		
		
//		Map<String, Object> map = httpUtil.sendGetRequest("http://hq.dianxinnews.com");
		
		Map<String, String> ent = new HashMap<>();
		ent.put("grant_type", "authorization_code");
		ent.put("client_id", "F1BBFABDF85CE5076EC638E06F97930F");
		ent.put("client_secret", "7290f39a2c934743baaf8ccac99656ff");
		ent.put("scope", "read");
		ent.put("redirect_uri", "http://www.597zg.com/index.php");
		ent.put("code", "vjbszh");
		
		Map<String, Object> map = httpUtil.sendPostMapRequest("https://oauth.jd.com/oauth/token", ent, "utf-8");
		
		System.out.println(map);
		
	}
}
