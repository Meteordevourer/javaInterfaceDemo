package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.WlDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DeviceMapper {
    WlDevice selectDeviceIsExists(Map<String, Object> param);
}
