package com.webdemo.weapi.utils.http;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpsUtil {
	// GPS地址转换请求超时时间 (毫秒)
	private static final int GPS_DATA_TIMEOUT = 4000;
	
	private static class DefaultTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	private static HttpsURLConnection getHttpsURLConnection(String uri) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		SSLSocketFactory ssf = ctx.getSocketFactory();

		URL url = new URL(uri);
		HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
		httpsConn.setConnectTimeout(GPS_DATA_TIMEOUT);
		httpsConn.setSSLSocketFactory(ssf);
		httpsConn.setHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		httpsConn.setDoInput(true);
		httpsConn.setDoOutput(true);
		return httpsConn;
	}

	public static String httpsGetRequest(String url) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream = null;
		HttpsURLConnection hc = null;
		try {
			hc = getHttpsURLConnection(url);
			hc.setRequestMethod("GET");
			hc.connect();

			inputStream = hc.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			return buffer.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			// 释放资源
			if (null != inputStream) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					throw e;
				}
			}
			if (null != hc) {
				hc.disconnect();
			}
		}
	}
}
