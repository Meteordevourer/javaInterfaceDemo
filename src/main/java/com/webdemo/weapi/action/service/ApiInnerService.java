package com.webdemo.weapi.action.service;


import com.webdemo.weapi.action.domain.AlarmLog;
import com.webdemo.weapi.action.domain.ResponseDataListVO;
import com.webdemo.weapi.action.domain.ResponsePageVO;
import com.webdemo.weapi.action.domain.TokenInfo;
import com.webdemo.weapi.action.domain.show.GpsLocation;

import com.webdemo.weapi.action.domain.show.app.AlarmShow2;
import com.zte.conn.parser.base.protobuf.GpsData.gpsData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 终端内部service
 */
public interface ApiInnerService {
	
	
	TokenInfo handlerParamsData(HttpServletRequest request, HttpServletResponse response,
								Map<String, String> params, ResponseDataListVO responseDataListVO);

	TokenInfo handlerParamsData(HttpServletRequest request, HttpServletResponse response,
                                Map<String, String> params, ResponsePageVO responsePageVO);

//	TokenInfo handlerParamsData(HttpServletRequest request, HttpServletResponse response,
 //                               Map<String, String> params, ResponseDataObjectVO responseDataObjectVO);


	void getRequestParam(HttpServletRequest request, Map<String, String> paramMap, String... params) throws Exception;

	Boolean checkRequestParam(Map<String, String> paramMap, String... params);

	Boolean checkRequestParamObject(Map<String, Object> paramMap, String... params);

	int handlerPageRequestParam(Map<String, String> paramMap);


	void handlerCheckParamFail(ResponsePageVO responsePageVO, HttpServletResponse response, String string,
                               String string2);

	void handResponse(HttpServletResponse response, String json);

	void handlerCheckParamFail(ResponseDataListVO responseDataListVO, HttpServletResponse response, String resCode,
                               String resMsg);
	
	Map<String, Object> paramToPage(Map<String, Object> param, Map<String, String> paramMap) ;
	
	GpsLocation gpsDataToGpsLocation(gpsData data, TokenInfo flag, int isAdd);
	

	
//	GpsLocation alarmToGpsLocation(AlarmLogEntity alarm, int timeType);
	
	AlarmShow2 alarmEntityToAlarmShow2(AlarmLog alarm);
	
	public void loadAlarmTypeRedis();
}