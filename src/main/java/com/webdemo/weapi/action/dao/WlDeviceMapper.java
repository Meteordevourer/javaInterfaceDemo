package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.entity.UWlDevice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WlDeviceMapper {
    int updateByPrimaryKeySelective(UWlDevice record);
}
