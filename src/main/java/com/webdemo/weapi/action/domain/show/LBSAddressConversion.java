package com.webdemo.weapi.action.domain.show;



import com.webdemo.weapi.utils.Constant;
import com.webdemo.weapi.utils.http.HttpsUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description: Google、百度物理地址转化
 * @author: 许智皓
 * @date: 2018年7月31日 下午2:23:22
 * @Company: GosuncnWelink.
 * @version: V1.01
 * 
 * @updateDate: 
 * @update:
 */
@Slf4j
public class LBSAddressConversion {
	
	private static final String GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
	
	private static final String BAIDU_API_URL = "http://api.map.baidu.com/geocoder/v2/";
	
	private static final String GOOGLE_STATUS_OK = "OK";
	
	private static String baiduMapKey = "xpf9QKC3RwEfqnBtvHf35Fur8pOwxu6g";
	
	private static String googleMapKey = "AIzaSyCkETT3ivOiCKCthzKWRLE8fRKFgiviuVI";

    /**
     * 百度api物理地址转换
     * @param lng 经度
     * @param lat 纬度
     * @return 物理地址
     */
	public static String getBaiduAddress(Double lng, Double lat) {
    	
		String address = Constant.UNABLE_TO_LOCATE_ZH;
		
		try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(BAIDU_API_URL);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("ak", baiduMapKey));
			params.add(new BasicNameValuePair("coordtype", "wgs84ll"));
			params.add(new BasicNameValuePair("pois", "0"));
			params.add(new BasicNameValuePair("location", lat + "," + lng));
			params.add(new BasicNameValuePair("output", "json"));
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
			// 请求内容
            CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String json = EntityUtils.toString(entity).trim();
			if (StringUtils.isNotBlank(json)) {
				JSONObject obj = JSONObject.fromObject(json);
				Integer status = obj.getInt("status");
				if (status == 0) {
					JSONObject addrObj = obj.getJSONObject("result").getJSONObject("addressComponent");
					address = addrObj.getString("province");
					address += addrObj.getString("city");
					address += addrObj.getString("district");
					address += addrObj.getString("street");
					address += addrObj.getString("street_number");
				}
			}
		} catch (Exception e) {
			log.error("getDaiBuAddress request baidu map error, exception:{}", e);
		}
		return address;
	}

    /**
     * 谷歌api物理地址转换
     * @param lng 经度
     * @param lat 纬度
     * @return 物理地址
     */
	public static String getGoogleAddress(Double lng, Double lat) {
		String address = Constant.UNABLE_TO_LOCATE_EN;
		try {
			String url = GOOGLE_API_URL + "?latlng=" + lat + "," + lng + "&key=" + googleMapKey + "&language=en";

			String json = HttpsUtil.httpsGetRequest(url);
			if (StringUtils.isNotBlank(json)) {
				JSONObject obj = JSONObject.fromObject(json);
				String status = obj.getString("status");
				JSONArray addrList = obj.getJSONArray("results");
				if (GOOGLE_STATUS_OK.equalsIgnoreCase(status) && null != addrList && addrList.size() > 0) {
					JSONObject addressObj = addrList.getJSONObject(0);
					address = addressObj.getString("formatted_address");
				}
			}
		} catch (Exception e) {
			log.error("getGoogleAddress request google map error, exception:{}", e);
		}
		return address;
	}
	
	public static void main(String[] args) {
		
		String s = LBSAddressConversion.getGoogleAddress(17.06257D,22.59693D);
		//String s = LBSAddressConversion.getBaiduAddress(17.06257D,22.59693D);
		System.out.println(s);
	}
}
