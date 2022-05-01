package com.webdemo.weapi.action.service;

import com.webdemo.weapi.action.domain.ResponseDataListVO;
import com.webdemo.weapi.action.domain.ResponsePageVO;
import com.webdemo.weapi.action.domain.WlVehicleTravel;

import java.util.Map;

public interface ITravelService {

    ResponseDataListVO selectVehicleTravelList(Map<String,Object> param, ResponseDataListVO responseData);

    ResponsePageVO selectVehicleTravelInfoList(Map<String,Object> param,ResponsePageVO responseData);

    ResponsePageVO selectVehicleTravelNowDateList(Map<String,Object> param, ResponsePageVO responseData);

    WlVehicleTravel selectByTravelId(Map<String,Object> param);

    int updateTravelMax(WlVehicleTravel param);

    ResponsePageVO selectVehicleTravelListByDate(Map<String,Object> param,ResponsePageVO responseData);
}
