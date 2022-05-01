package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.show.app.VehicleInfoShow;

import java.util.List;
import java.util.Map;

public interface VehicleMapper {
    List<VehicleInfoShow> selectVehicleList(Map<String,Object> param);

    long selectVehicleListCount(Map<String ,Object> param);



    long selectVehicleIsExists(Map<String,Object> param);
}
