package com.webdemo.weapi.utils;

import com.webdemo.weapi.action.domain.entity.WlAlarmType;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存数据寄存器
 * @author xuzhihao
 *
 */

public class CacheData {

    public static Map<String, WlAlarmType> alarmTypeMap = new HashMap<String, WlAlarmType>();
}
