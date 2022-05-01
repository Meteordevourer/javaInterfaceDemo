package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.AlarmLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AlarmLogMapper {
    List<AlarmLog> selectAlarmByPage(Map<String, Object> param);
    Long selectAlarmByPageCount(Map<String, Object> param);
}
