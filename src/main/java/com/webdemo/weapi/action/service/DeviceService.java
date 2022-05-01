package com.webdemo.weapi.action.service;

import com.webdemo.weapi.action.domain.WlDevice;

import java.util.Map;

public interface DeviceService {
    WlDevice selectDeviceIsExists(Map<String, Object> param);
}
