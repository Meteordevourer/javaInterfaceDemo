package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.WlVehicleTravel;
import com.webdemo.weapi.action.domain.show.app.VehicleTravelNowDateShow;
import com.webdemo.weapi.action.domain.show.app.VehicleTravelShow;

import java.util.List;
import java.util.Map;

public interface TravelMapper {

    List<VehicleTravelShow> selectVehicleTravelList(Map<String,Object> param);

    long selectVehicleTravelInfoListCount(Map<String,Object> param);

    List<WlVehicleTravel> selectVehicleTravelInfoList(Map<String,Object> param);

    WlVehicleTravel selectByTravelId(Map<String,Object> param);

    int updateTravelMax(WlVehicleTravel param);

    long selectVehicleTravelNowDateListCount(Map<String,Object> param);

    List<VehicleTravelNowDateShow> selectVehicleTravelNowDateList(Map<String,Object>param);

    long selectVehicleTravelListByDateCount(Map<String,Object> param);

    List<WlVehicleTravel> selectVehicleTravelListByDate(Map<String,Object>param);
}
