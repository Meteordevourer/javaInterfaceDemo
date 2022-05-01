package com.webdemo.weapi.action.service;



import com.webdemo.weapi.action.domain.ResponsePageVO;

import java.util.Map;

public interface IVehicleService {

	ResponsePageVO selectVehicleList(Map<String, Object> param, ResponsePageVO responseData);

	long selectVehicleIsExists(Map<String, Object> param);

	ResponsePageVO selectVehicleSerierList(Map<String, Object> param, ResponsePageVO responseData);

	ResponsePageVO selectVehicleBrandList(Map<String, Object> param, ResponsePageVO responseData);

}
