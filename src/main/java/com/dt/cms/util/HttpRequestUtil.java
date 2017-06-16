package com.dt.cms.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url) {
		return sendGet(url, "UTF-8");
	}

	public static Header[] getHeaders(String url) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept-Encoding", "");
		try {
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			return httpResponse.getAllHeaders();

		} catch (SocketTimeoutException ste) {
			logger.error(ste.getMessage());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e) + ":" + url);
			return null;
		}
		return null;
	}

	public static String sendGet(String url, String charSet) {
		String result = "";
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept-Encoding", "");

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
				.setConnectionRequestTimeout(10000).build();
		httpGet.setConfig(requestConfig);
		try {
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 判断响应实体是否为空
			if (entity != null) {
				result = EntityUtils.toString(entity, charSet);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e) + ":" + url);
			return null;
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, Map<String, String> params) {
		String respContent = "";
		try {
			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(RequestConfig.DEFAULT);
			// 创建参数队列
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				formparams.add(nameValuePair);
			}
			UrlEncodedFormEntity entity;
			try {
				entity = new UrlEncodedFormEntity(formparams, "UTF-8");
				httpPost.setEntity(entity);

				HttpResponse httpResponse;
				// post请求
				httpResponse = closeableHttpClient.execute(httpPost);

				// getEntity()
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					// 打印响应内容
					respContent = EntityUtils.toString(httpEntity, "UTF-8");
				}
				// 释放资源
				closeableHttpClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return respContent;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static String sendJSON(String url, JSONObject jobj) throws ClientProtocolException, IOException {
		String respContent = null;
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build());
		httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
		httpPost.setHeader("Accept", "application/json");
		StringEntity entity = new StringEntity(jobj.toJSONString(), Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
        entity.setContentType("text/json");
		httpPost.setEntity(entity);
		
		HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		if (httpEntity != null) {
			respContent = EntityUtils.toString(httpEntity, "UTF-8");
		}
		// 释放资源
		closeableHttpClient.close();
		return respContent;

	}

	public static InputStream getPicStream(String picURI) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(picURI);
			ResponseHandler<InputStream> responseHandler = new ResponseHandler<InputStream>() {
				@Override
				public InputStream handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						if (entity.isStreaming()) {
							InputStream picStream = new ByteArrayInputStream(EntityUtils.toByteArray(entity));
							return picStream;
						}
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
					return null;
				}
			};
			return httpclient.execute(httpget, responseHandler);
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return null;
	}

	/**
	 * 将cookie封装到Map里面  
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 从request中解析出提交的参数
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> parseRequestParams(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String[]> requestMap = request.getParameterMap();
		if (requestMap != null && requestMap.size() > 0) {
			for (String key : requestMap.keySet()) {
				String[] values = requestMap.get(key);
				map.put(key, values[0].trim());
			}
		}
		return map;
	}
}