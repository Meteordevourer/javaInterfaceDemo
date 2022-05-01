package com.webdemo.weapi.utils.http;



import com.webdemo.weapi.action.domain.entity.ResponseObject;
import com.webdemo.weapi.utils.Constant;
import com.webdemo.weapi.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求公共类
 * @author Magicyo
 */
@Slf4j
public final class HttpClientHelper {
	/**
	 * 日志对象
	 */

	/**
	 * 获取HttpClient对象
	 * 
	 * @return {@link HttpClient}
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	static CloseableHttpClient getHttpClient() throws KeyManagementException, NoSuchAlgorithmException
    {
		RequestConfig defaultRequestConfig = RequestConfig.custom()
                // 创建连接的最长时间
				.setConnectTimeout(30000)
                // 从连接池中获取到连接的最长时间
				.setConnectionRequestTimeout(10000)
                // 数据传输的最长时间
				.setSocketTimeout(60000)
				.build();
        return HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).build();
	}

	/**
	 * 得到post方式的Http请求对象
	 * @param uri
	 * @return
	 */
	protected static HttpPost getHttpPost(String uri, String param) {
	    HttpPost httpPost = new HttpPost(uri);
        // 构造消息头
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("Connection", "Close");
        // 构建消息实体
        StringEntity entity = new StringEntity(param, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
		return httpPost;
	}

	/**
	 * 得到get方式的Http请求对象
	 * @param uri
	 * @return
	 */
	protected static HttpGet getHttpGet(String uri) {
        HttpGet httpGet = new HttpGet(uri);
        // 构造消息头
        httpGet.setHeader("Content-type", "application/json; charset=utf-8");
        httpGet.setHeader("Connection", "Close");
		return httpGet;
	}

	/**
	 * 得到delete方式的Http请求对象
	 * @param uri
	 * @return
	 */
	protected static HttpDelete getHttpDelete(String uri) {
		// 创建delete请求
        HttpDelete httpDelete = new HttpDelete(uri);
        httpDelete.setHeader("Content-type", "application/json; charset=utf-8");
        httpDelete.setHeader("Connection", "Close");
		return httpDelete;
	}

    /**
     * 得到put方式的Http请求对象
     * @param uri
     * @return
     */
    protected static HttpPut getHttpPut(String uri, String param) {
		HttpPut httpPut = new HttpPut(uri);

		// 构造消息头
		httpPut.setHeader("Content-type", "application/json; charset=utf-8");
		httpPut.setHeader("Connection", "Close");
		// 构建消息实体
		StringEntity entity = new StringEntity(param, Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
		// 发送Json格式的数据请求
		entity.setContentType("application/json");
		httpPut.setEntity(entity);
        return httpPut;
    }

    /**
     * POST请求
     * @param url 请求路径
     * @param param 请求参数
     * @return 调用接口返回值
     * @throws Exception
     */
	public static String sendPostRequest(String url, String param) throws Exception {
		String responseBody = null;
		HttpPost post = null;
		try {
			HttpClient httpClient = getHttpClient();
			post = getHttpPost(url, param);
			HttpResponse response = httpClient.execute(post);
			responseBody = readInputStream(response.getEntity().getContent());
			log.info("HTTP请求公共类 --> POST请求, url:{}, 返回消息:{}", url, responseBody);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(post != null){
                post.releaseConnection();
                Thread.sleep(500);
			}
		}
		return responseBody;
	}


    /**
     * GET请求
     * @param uri
     * @return
     * @throws Exception
     */
	public static String sendGetRequest(String uri) throws Exception {
		// 创建客户端
		HttpClient httpclient = getHttpClient();
		HttpGet get = getHttpGet(uri);
		String responseBody = null;
		try {
			log.info("GET 请求url:{}", uri);
			HttpResponse response = httpclient.execute(get);
			responseBody = readInputStream(response.getEntity().getContent());
			//log.info("GET 返回结果:{}", responseBody);
		} finally {
			if(get != null){
                get.releaseConnection();
                Thread.sleep(500);
			}
		}
		return responseBody;
	}

	public static String sendDeleteRequest(String uri) throws Exception {
		//long startTime = System.currentTimeMillis();
		// 创建客户端
		HttpClient httpclient = getHttpClient();
		HttpDelete httpDelete = getHttpDelete(uri);
		// 构造消息头
		String responseBody = null;
		try {
			HttpResponse response = httpclient.execute(httpDelete);
			responseBody = readInputStream(response.getEntity().getContent());
            log.debug("接口调用公共方法 --> DELETE方法, 请求路径:{}, 返回消息:{}", uri, response);
		} finally {
			if(httpDelete != null){
				try {
					httpDelete.releaseConnection();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					throw new Exception(e);
				}
			}
		}
		return responseBody;
	}

    public static String sendPutRequest(String url, String param)  throws Exception {
		String responseBody = null;
		HttpPut put = null;
		try {
			HttpClient httpClient = getHttpClient();
			put = getHttpPut(url, param);

			HttpResponse response = httpClient.execute(put);
			responseBody = readInputStream(response.getEntity().getContent());
			log.debug("接口调用公共方法 --> DELETE方法, 请求路径:{}, 请求参数:{} 返回消息:{}", url, param, response);
		}finally{
			if(put != null){
				put.releaseConnection();
				Thread.sleep(500);
			}
		}
		return responseBody;
    }

	/**
	 * 处理返回文件流
	 * 
	 * @param inputStream
	 *            {@link InputStream} 输入流
	 * @return {@link String}
	 * @throws IOException
	 */
	private static String readInputStream(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			buffer.append(line + "\n");
		}
		inputStream.close();
		return buffer.toString();
	}

    /**
     * 发送短信
     * @param url 请求路径
     * @param paramsMap 请求参数
     * @return 发送短信结果
     */
	public static String smsPost(String url, Map<String, String> paramsMap) {
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				  .setSocketTimeout(3000)
				  .setConnectTimeout(6000)
				  .setConnectionRequestTimeout(5000)
				  .build();
		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity, "UTF-8");
                log.debug("接口调用公共方法 --> 发送短信, 请求路径:{}, 返回消息:{}", url, responseText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != response) {
					response.close();
				}
			} catch (Exception e) {
               log.error("接口调用公共方法 --> 发送短信, 发生异常:{}", e);
			}
		}
		return responseText;
	}
	
	
	/**
	 * 封装返回信息
	 * @param response 返回response
	 * @param responseStr 返回消息
	 */
	public static void handResponse(HttpServletResponse response, Object data) {
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(GsonUtil.getInstance().toJson(ResponseObject.success(data)));
		} catch (IOException e) {
			log.error("ApiDataController response exception:{}", e);
		}
	}
	
	
	public static void handErrorResponse(HttpServletResponse response, Object data) {
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			ResponseObject error = new ResponseObject();
			error.setCode(Constant.RESPONSE_ERROR_CODE);
			error.setResMsg(String.valueOf(data));
			
			response.getWriter().write(GsonUtil.getInstance().toJson(ResponseObject.success(data)));
		} catch (IOException e) {
			log.error("exception:{}", e);
		}
	}

	
	
	
    /**
     * 检查方法入参是否为空
     *
     * @param request  请求参数
     * @param response 返回参数
     * @return 校验后的请求参数
     */
    public static String checkParamsUtil(HttpServletRequest request, HttpServletResponse response) {
        String dataJson;
        try {
            BufferedReader ss = request.getReader();
            ss.mark(Constant.MASK_LENGTH);
            dataJson = ss.readLine();
        } catch (IOException e) {
            log.error("[HttpClientHelper.checkParamsUtil]网络请求 --> 检查参数方法, 发生未知异常:{}", e);
            handResponse(response, "请求失败:发生未知异常,"+e);
            return null;
        }

        if (StringUtils.isBlank(dataJson)) {
            log.error("[HttpClientHelper.checkParamsUtil]网络请求  --> 检查参数方法, 参数为空");
            handResponse(response, "请求失败:参数为空");
            return null;
        }

        return dataJson;
    }
    
    
    /**
     * 检查方法入参是否为空
     *
     * @param request  请求参数
     * @param response 返回参数
     * @return 校验后的请求参数
     */
	public static boolean checkParamsUtil(HttpServletRequest request, HttpServletResponse response, Object obj) {

		if (null == obj) {

			log.error("[HttpClientHelper.checkParamsUtil]网络请求  --> 检查参数方法, 参数为空");
			handResponse(response, "请求失败:参数为空");
			return false;
		}

		return true;
	}
	public static boolean checkParamsUtil(HttpServletRequest request, HttpServletResponse response, List<Object> objs) {

		if (null == objs || 0 < objs.size()) {

			log.error("[HttpClientHelper.checkParamsUtil]网络请求  --> 检查参数方法, 参数为空");
			handResponse(response, "请求失败:参数为空");
			return false;
		}

		return true;
	}
}
