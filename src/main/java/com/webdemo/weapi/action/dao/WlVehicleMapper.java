package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.entity.WlVehicle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WlVehicleMapper {
    int insertSelective(WlVehicle record);

}
