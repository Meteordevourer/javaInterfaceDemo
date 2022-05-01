package com.webdemo.weapi.action.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.webdemo.weapi.action.domain.*;
import com.webdemo.weapi.action.domain.entity.WlAlarmType;
import com.webdemo.weapi.action.domain.show.GpsLocation;
import com.webdemo.weapi.action.domain.show.LBSAddressConversion;
import com.webdemo.weapi.action.domain.show.app.AlarmShow2;
import com.webdemo.weapi.action.service.ApiInnerService;
import com.webdemo.weapi.action.service.IEmployeeService;
//import com.webdemo.weapi.utils.CacheData;
import com.webdemo.weapi.utils.CacheData;
import com.webdemo.weapi.utils.DateUtil;
import com.webdemo.weapi.utils.PositionUtil;

import com.webdemo.weapi.utils.StringBaseUtils;
import com.zte.conn.parser.base.protobuf.GpsData.gpsData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 *
 */
@Slf4j
@Service("apiInnerService")
public class ApiInnerServiceImpl implements ApiInnerService {
	;


	@Autowired
	private IEmployeeService employeeService;



	@Override
	public TokenInfo handlerParamsData(HttpServletRequest request, HttpServletResponse response,
									   Map<String, String> params, ResponseDataListVO responseDataListVO) {

		TokenInfo ti = new TokenInfo();

		String resCode = "";
		String resMsg = "";
		Boolean flag = true;
		try {
			// 赋值--将全部http的值全部读取出来
			getRequestParam(request, params, ApiConconts.ACCESS_TOKEN, ApiConconts.RES_SRT, ApiConconts.VERSION,
					ApiConconts.DATA, ApiConconts.PAGE, ApiConconts.PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			resCode = ApiConconts.PARAM_INVALID.toString();
			resMsg = ApiConconts.errorInfoMap.get(ApiConconts.PARAM_INVALID);
			flag = false;
			log.error("参数赋值失败，参数传入失败");
		}

		// 校验参数是否为空
		if (flag) {
			Boolean paramFlag = checkRequestParam(params, ApiConconts.ACCESS_TOKEN, ApiConconts.VERSION,
					ApiConconts.DATA);
			if (!paramFlag) {
				resCode = ApiConconts.PARAM_EMPTY.toString();
				resMsg = ApiConconts.errorInfoMap.get(ApiConconts.PARAM_EMPTY);
				flag = false;
				log.error("参数校验异常，参数传入为空");
			}
		}

		// 校验token是否有效
		if (flag) {

			Employee user = employeeService.checkToken(params.get(ApiConconts.ACCESS_TOKEN));
			ti.setEmployee(user);
			if (user == null || user.getTokenValidityTime().getTime() < new Date().getTime()) {

				resCode = ApiConconts.TOKEN_INVALID.toString();
				resMsg = ApiConconts.errorInfoMap.get(ApiConconts.TOKEN_INVALID);
				flag = false;
				log.error("参数校验异常，token过期或无效");
			}

		}

		responseDataListVO.setResCode(resCode);
		responseDataListVO.setResMsg(resMsg);
		responseDataListVO.setResSrt(params.get("resSrt"));
		responseDataListVO.setData(new ArrayList<Object>());
		responseDataListVO.setVersion(params.get(ApiConconts.VERSION));
		ti.setCheck(flag);
		
		return ti;
	}



	@Override
	public TokenInfo handlerParamsData(HttpServletRequest request, HttpServletResponse response,
                                       Map<String, String> params, ResponsePageVO responsePageVO) {

		TokenInfo ti = new TokenInfo();
		String resCode = "";
		String resMsg = "";
		Boolean flag = true;
		try {
			// 赋值--将全部http的值全部读取出来
			getRequestParam(request, params, ApiConconts.ACCESS_TOKEN, ApiConconts.RES_SRT, ApiConconts.VERSION,
					ApiConconts.DATA, ApiConconts.PAGE, ApiConconts.PAGE_SIZE, ApiConconts.ORDER_BY,
					ApiConconts.DESC_OR_ASC);

		} catch (Exception e) {
			e.printStackTrace();
			resCode = ApiConconts.PARAM_INVALID.toString();
			resMsg = ApiConconts.errorInfoMap.get(ApiConconts.PARAM_INVALID);
			flag = false;
			log.error("参数赋值失败，参数传入失败");
		}

		// 校验参数是否为空
		if (flag) {
			Boolean paramFlag = checkRequestParam(params, ApiConconts.ACCESS_TOKEN, ApiConconts.VERSION,
					ApiConconts.DATA);
			if (!paramFlag) {
				resCode = ApiConconts.PARAM_EMPTY.toString();
				resMsg = ApiConconts.errorInfoMap.get(ApiConconts.PARAM_EMPTY);
				flag = false;
				log.error("参数校验异常，参数传入为空");
			}
		}

		// 校验token是否有效
		if (flag) {

			Employee user = employeeService.checkToken(params.get(ApiConconts.ACCESS_TOKEN));
			ti.setEmployee(user);

			if (user == null || user.getTokenValidityTime().getTime() < new Date().getTime()) {

				resCode = ApiConconts.TOKEN_INVALID.toString();
				resMsg = ApiConconts.errorInfoMap.get(ApiConconts.TOKEN_INVALID);
				flag = false;
				log.error("参数校验异常，token过期或无效");
			}

		}

		// 校验分页参数,0：正常，1:参数为空，2：参数异常
		// if (flag) {
		int pageFlag = handlerPageRequestParam(params);
		if (pageFlag != 0) {
			if (pageFlag == 1) {
				resCode = ApiConconts.PARAM_EMPTY.toString();
				resMsg = ApiConconts.errorInfoMap.get(ApiConconts.PARAM_EMPTY);
			} else {
				resCode = ApiConconts.PARAM_INVALID.toString();
				resMsg = ApiConconts.errorInfoMap.get(ApiConconts.PARAM_INVALID);
			}
			flag = false;
			log.error("分页查询, 请求参数校验失败");

		}
		// }

		responsePageVO.setPage(Integer.parseInt(params.get(ApiConconts.PAGE).toString()));// 当前页码
		responsePageVO.setPageSize(Integer.parseInt(params.get(ApiConconts.PAGE_SIZE).toString()));// 每页多少
		responsePageVO.setTotalRows(0L);// 总条数
		responsePageVO.setPageNum(0);
		responsePageVO.setData(new ArrayList<Object>());

		responsePageVO.setVersion(params.get(ApiConconts.VERSION));
		responsePageVO.setResCode(resCode);
		responsePageVO.setResMsg(resMsg);
		responsePageVO.setResSrt(params.get(ApiConconts.RES_SRT));

		ti.setCheck(flag);
		
		return ti;
	}

	@Override
	public void getRequestParam(HttpServletRequest request, Map<String, String> paramMap, String... params)
			throws Exception {
		// 获取全部参数
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			for (String str : params) {
				if (paraName.equals(str)) {

					if (ApiConconts.DESC_OR_ASC.equals(paraName)) {
						paramMap.put(str, request.getParameter(str).toUpperCase());
					} else {
						paramMap.put(str, request.getParameter(str));
					}

					continue;
				}
			}
		}

	}

	@Override
	public Boolean checkRequestParam(Map<String, String> paramMap, String... params) {
		Boolean flag = true;
		for (String str : params) {
			if (!paramMap.containsKey(str) || StringUtils.isBlank(paramMap.get(str))) {
				flag = false;
				break;
			}
		}

		return flag;
	}

	@Override
	public Boolean checkRequestParamObject(Map<String, Object> paramMap, String... params) {
		Boolean flag = true;
		for (String str : params) {
			if (!paramMap.containsKey(str)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Override
	public int handlerPageRequestParam(Map<String, String> paramMap) {
		// 判断传入的页数和当前页是否正确
		// 没有传页数参数，需要给出默认值
		if (paramMap.get(ApiConconts.PAGE_SIZE) == null) {
			paramMap.put(ApiConconts.PAGE_SIZE, ApiConconts.PAGE_SIZE_DEFAULT_VALUE);
		}
		// 没有传当前页参数，需要给出默认值
		if (paramMap.get(ApiConconts.PAGE) == null) {
			paramMap.put(ApiConconts.PAGE, ApiConconts.PAGE_DEFAULT_VALUE);
		}
		// 传参数-页数、当前页，但是参数值传空
		if (StringUtils.isBlank(paramMap.get(ApiConconts.PAGE_SIZE))
				|| StringUtils.isBlank(paramMap.get(ApiConconts.PAGE))) {
			return 1;
		}
		// 传参数-页数、当前页，但是参数值传错
		if (!StringUtils.isNumeric(paramMap.get(ApiConconts.PAGE_SIZE))
				|| !StringUtils.isNumeric(paramMap.get(ApiConconts.PAGE))
				|| Integer.parseInt(paramMap.get(ApiConconts.PAGE_SIZE)) < 0
				|| Integer.parseInt(paramMap.get(ApiConconts.PAGE)) < 0) {
			return 2;
		}
		// 设置数据库的开始条数
		Integer pageSize = paramMap.containsKey(ApiConconts.PAGE_SIZE)
				? Integer.parseInt(paramMap.get(ApiConconts.PAGE_SIZE))
				: Integer.parseInt(ApiConconts.PAGE_SIZE_DEFAULT_VALUE);
		paramMap.put(ApiConconts.PAGE_START,
				String.valueOf((Integer.parseInt(paramMap.get(ApiConconts.PAGE)) - 1) * pageSize));

		return 0;
	}

	@Override
	public void handlerCheckParamFail(ResponsePageVO responsePageVO, HttpServletResponse response, String string, String string2) {

	}

	@Override
	public void handResponse(HttpServletResponse response, String json) {

	}

	@Override
	public void handlerCheckParamFail(ResponseDataListVO responseDataListVO, HttpServletResponse response, String resCode, String resMsg) {

	}


	public static String getRequestURL() {

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String scheme = req.getScheme(); // http
		String contextPath = req.getContextPath(); // /mywebapp
		String servletPath = req.getServletPath(); // /servlet/MyServlet
		String pathInfo = req.getPathInfo(); // /a/b;c=123
		// String queryString = req.getQueryString(); // d=789

		// Reconstruct original requesting URL
		StringBuilder url = new StringBuilder();
		url.append(scheme).append("://").append(req.getRemoteAddr());
		url.append(":").append(req.getRemotePort());

		url.append(contextPath).append(servletPath);

		if (pathInfo != null) {
			url.append(pathInfo);
		}
		// if (queryString != null) {
		// url.append("?").append(queryString);
		// }
		return url.toString();
	}

	public static String getRequestRemoteIp() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getRemoteAddr();
	}

	public static String getToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		return request.getParameter("accessToken");
	}

	public static String getParams() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		JSONObject jobject = new JSONObject();

		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			jobject.put(paraName, request.getParameter(paraName));
		}

		return JSONObject.toJSONString(jobject);
	}

	@Override
	public Map<String, Object> paramToPage(Map<String, Object> param, Map<String, String> paramMap) {

		param.put(ApiConconts.PAGE, paramMap.get(ApiConconts.PAGE));
		param.put(ApiConconts.PAGE_SIZE, paramMap.get(ApiConconts.PAGE_SIZE));
		param.put(ApiConconts.ORDER_BY, paramMap.get(ApiConconts.ORDER_BY));
		param.put(ApiConconts.DESC_OR_ASC, paramMap.get(ApiConconts.DESC_OR_ASC));

		return param;
	}

	@Override
	public GpsLocation gpsDataToGpsLocation(gpsData data, TokenInfo flag, int isAdd) {

		GpsLocation gpsLocation = new GpsLocation();

		gpsLocation.setGpsSpeed(data.getGpsSpeed());// GPS速度
		gpsLocation.setLatitude(data.getLatitude());// 纬度
		gpsLocation.setLongitude(data.getLongitude());// 经度
		gpsLocation.setHeight(data.getAltitude());// 高度
		gpsLocation.setHeading(data.getDirection());// 方向
		gpsLocation.setLongitudeType(data.getLongitudeFlag());// 经度型（0东经，1西经）
		gpsLocation.setLatitudeType(data.getLatitudeFlag());// 纬度类型（0南纬，1北纬）
		gpsLocation.setSatellitesNumber(data.getSatellites());// 卫星数
		gpsLocation.setGpsTime(DateUtil.localToUTC_Time(data.getMsgHeader().getObdTime()));// 时间
		gpsLocation.setAddressText("");// 地址
		
		if (1 == isAdd) {
			
			String address = null;		
			// 地址转化
			if(PositionUtil.isInsideChina(data.getLatitude(), data.getLongitude())) {
				address = LBSAddressConversion.getBaiduAddress(data.getLongitude(), data.getLatitude());
			}else {
				address = LBSAddressConversion.getGoogleAddress(data.getLongitude(), data.getLatitude());
			}
		
//			if (null != flag && 10000 == flag.getEmployee().getLanguageId()) {
//				address = LBSAddressConversion.getGoogleAddress(data.getLongitude(), data.getLatitude());
//			} else {// 默认转化为中文
//				address = LBSAddressConversion.getBaiduAddress(data.getLongitude(), data.getLatitude());
//			}

			gpsLocation.setAddressText(address != null ? address : "");// 地址
			
		}

		return gpsLocation;
	}

	@Override
	public void loadAlarmTypeRedis() {

	}
	@Override
	public AlarmShow2 alarmEntityToAlarmShow2(AlarmLog alarm) {

		AlarmShow2 as = new AlarmShow2();

		as.setAlarmId(alarm.getAlarmId());// 告警id
		as.setAlarmName(alarm.getAlarmName());// 告警名称
		as.setAlarmTime(DateUtil.localToUTC_Time(DateUtil.dateToDateString(alarm.getMachineTime())));// 告警时间， yyyy-MM-ddhh：mm：ss -- machine_time
		as.setAlarmDistance(alarm.getAlarmDistance() != null ? alarm.getAlarmDistance().floatValue() : -9999F);
		as.setOnlineState(StringBaseUtils.vehicleState(alarm.getOnlineState() != null ? alarm.getOnlineState() : 255, alarm.getAcc() != null ? alarm.getAcc() : 255));
		as.setPlateNumber(alarm.getVehicleNumber());

//		Object alarmObj = redisTemplate.opsForHash().get(Constant.ALARM_TYPE_LIST_HASH_REDLS, alarm.getAlarmId());
//		if (null != alarmObj) {
//
//			WlAlarmType type = GsonUtil.getInstance().fromJson(alarmObj.toString(), WlAlarmType.class);
//			as.setDataType(type.getAlarmType());
//		} else {
//			as.setDataType(-9999);
//		}

		WlAlarmType type = CacheData.alarmTypeMap.get(alarm.getAlarmId());
		if (null != type) {
			as.setDataType(type.getAlarmClass());
		}else {
			as.setDataType(-9999);
		}


		StringBuffer alarmPrivateValue = new StringBuffer();

		alarmPrivateValue
				.append("{speed_before:").append(alarm.getSpeedBefore() != null ? alarm.getSpeedBefore() : -9999).append(",")
				.append("speed_after:").append(alarm.getSpeedAfter() != null ? alarm.getSpeedAfter() : -9999).append(",")
				.append("speed_value:").append(alarm.getSpeedValue() != null ? alarm.getSpeedValue() : -9999).append(",")
				.append("reserved1:").append(alarm.getReserved1() != null ? alarm.getReserved1() : "").append(",")
				.append("reserved2:").append(alarm.getReserved2() != null ? alarm.getReserved2() : "").append(",")
				.append("reserved3:").append(alarm.getReserved3() != null ? alarm.getReserved3() : "")
				.append("}");

		as.setAlarmPrivateValue(null != alarmPrivateValue ? alarmPrivateValue.toString() : "");


		GpsLocation startGpsLocation = new GpsLocation();
		startGpsLocation.setGpsTime(null != alarm.getStartTime() ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(alarm.getStartTime())) : -9999);
		startGpsLocation.setLongitude(null != alarm.getStartLongitude() ? alarm.getStartLongitude().doubleValue() : -9999);
		startGpsLocation.setLatitude(null != alarm.getStartLatitude() ? alarm.getStartLatitude().doubleValue() : -9999);
		startGpsLocation.setAddressText(null != alarm.getStartLocation() ? alarm.getStartLocation() : "");
		startGpsLocation.setGpsSpeed(-9999F);//GPS速度
		startGpsLocation.setHeight(-9999F);//高度
		startGpsLocation.setHeading(-9999F);//方向
		startGpsLocation.setLongitudeType(-9999);//经度型（0东经，1西经）
		startGpsLocation.setLatitudeType(-9999);//纬度类型（0南纬，1北纬）
		startGpsLocation.setSatellitesNumber(-9999);//卫星数
		as.setStartGpsLocation(startGpsLocation);

		GpsLocation endGpsLocation = new GpsLocation();
		endGpsLocation.setGpsTime(null != alarm.getEndTime() ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(alarm.getEndTime())) : -9999);
		endGpsLocation.setLongitude(null != alarm.getEndLongitude() ? alarm.getEndLongitude().doubleValue() : -9999);
		endGpsLocation.setLatitude(null != alarm.getEndLatitude() ? alarm.getEndLatitude().doubleValue() : -9999);
		endGpsLocation.setAddressText(null != alarm.getEndLocation() ? alarm.getEndLocation() : "");
		endGpsLocation.setGpsSpeed(-9999F);//GPS速度
		endGpsLocation.setHeight(-9999F);//高度
		endGpsLocation.setHeading(-9999F);//方向
		endGpsLocation.setLongitudeType(-9999);//经度型（0东经，1西经）
		endGpsLocation.setLatitudeType(-9999);//纬度类型（0南纬，1北纬）
		endGpsLocation.setSatellitesNumber(-9999);//卫星数
		as.setEndGpsLocation(endGpsLocation);

		return as;
	}
//	@Override
//	public AlarmShow alarmEntityToAlarmShow(AlarmLogEntity alarm) {
//
//		AlarmShow as = new AlarmShow();
//
//		as.setAlarmId(alarm.getAlarmId());// 告警id
//		as.setAlarmName(alarm.getAlarmName());// 告警名称
//		as.setAlarmTime(DateUtil.localToUTC_Time(DateUtil.dateToDateString(alarm.getMachineTime())));// 告警时间， yyyy-MM-ddhh：mm：ss -- machine_time
//		as.setAlarmDistance(alarm.getAlarmDistance() != null ? alarm.getAlarmDistance().floatValue() : -9999F);
//
//
////		Object alarmObj = redisTemplate.opsForHash().get(Constant.ALARM_TYPE_LIST_HASH_REDLS, alarm.getAlarmId());
////		if (null != alarmObj) {
////
////			WlAlarmType type = GsonUtil.getInstance().fromJson(alarmObj.toString(), WlAlarmType.class);
////			as.setDataType(type.getAlarmType());
////		} else {
////			as.setDataType(-9999);
////		}
//
//		WlAlarmType type = CacheData.alarmTypeMap.get(alarm.getAlarmId());
//		if (null != type) {
//			as.setDataType(type.getAlarmClass());
//		}else {
//			as.setDataType(-9999);
//		}
//
//		StringBuffer alarmPrivateValue = new StringBuffer();
//
//		alarmPrivateValue
//				.append("{speed_before:").append(alarm.getSpeedBefore() != null ? alarm.getSpeedBefore() : -9999).append(",")
//				.append("speed_after:").append(alarm.getSpeedAfter() != null ? alarm.getSpeedAfter() : -9999).append(",")
//				.append("speed_value:").append(alarm.getSpeedValue() != null ? alarm.getSpeedValue() : -9999).append(",")
//				.append("reserved1:").append(alarm.getReserved1() != null ? alarm.getReserved1() : "").append(",")
//				.append("reserved2:").append(alarm.getReserved2() != null ? alarm.getReserved2() : "").append(",")
//				.append("reserved3:").append(alarm.getReserved3() != null ? alarm.getReserved3() : "")
//				.append("}");
//
//		as.setAlarmPrivateValue(null != alarmPrivateValue ? alarmPrivateValue.toString() : "");
//
//
//		GpsLocation startGpsLocation = new GpsLocation();
//		startGpsLocation.setGpsTime(null != alarm.getStartTime() ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(alarm.getStartTime())) : -9999);
//		startGpsLocation.setLongitude(null != alarm.getStartLongitude() ? alarm.getStartLongitude().doubleValue() : -9999);
//		startGpsLocation.setLatitude(null != alarm.getStartLatitude() ? alarm.getStartLatitude().doubleValue() : -9999);
//		startGpsLocation.setAddressText(null != alarm.getStartLocation() ? alarm.getStartLocation() : "");
//		startGpsLocation.setGpsSpeed(-9999F);//GPS速度
//		startGpsLocation.setHeight(-9999F);//高度
//		startGpsLocation.setHeading(-9999F);//方向
//		startGpsLocation.setLongitudeType(-9999);//经度型（0东经，1西经）
//		startGpsLocation.setLatitudeType(-9999);//纬度类型（0南纬，1北纬）
//		startGpsLocation.setSatellitesNumber(-9999);//卫星数
//		as.setStartGpsLocation(startGpsLocation);
//
//		GpsLocation endGpsLocation = new GpsLocation();
//		endGpsLocation.setGpsTime(null != alarm.getEndTime() ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(alarm.getEndTime())) : -9999);
//		endGpsLocation.setLongitude(null != alarm.getEndLongitude() ? alarm.getEndLongitude().doubleValue() : -9999);
//		endGpsLocation.setLatitude(null != alarm.getEndLatitude() ? alarm.getEndLatitude().doubleValue() : -9999);
//		endGpsLocation.setAddressText(null != alarm.getEndLocation() ? alarm.getEndLocation() : "");
//		endGpsLocation.setGpsSpeed(-9999F);//GPS速度
//		endGpsLocation.setHeight(-9999F);//高度
//		endGpsLocation.setHeading(-9999F);//方向
//		endGpsLocation.setLongitudeType(-9999);//经度型（0东经，1西经）
//		endGpsLocation.setLatitudeType(-9999);//纬度类型（0南纬，1北纬）
//		endGpsLocation.setSatellitesNumber(-9999);//卫星数
//		as.setEndGpsLocation(endGpsLocation);
//
//		return as;
//	}


}
