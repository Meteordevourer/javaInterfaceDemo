package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.entity.VehicleStateEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehicleStateMapper {
    VehicleStateEntity selectByPrimaryKey(String deviceCode);
    int updateByPrimaryKeySelective(VehicleStateEntity record);
    int insertSelective(VehicleStateEntity record);
}
