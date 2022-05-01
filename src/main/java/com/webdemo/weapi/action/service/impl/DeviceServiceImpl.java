package com.webdemo.weapi.action.service.impl;

import com.webdemo.weapi.action.dao.DeviceMapper;
import com.webdemo.weapi.action.domain.WlDevice;
import com.webdemo.weapi.action.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;


    /**
     * 查询当前部门是否存在其设备
     */
    @Override
    public WlDevice selectDeviceIsExists(Map<String, Object> param) {

        return deviceMapper.selectDeviceIsExists(param);
    }
}
