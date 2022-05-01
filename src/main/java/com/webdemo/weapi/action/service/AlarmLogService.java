package com.webdemo.weapi.action.service;

import com.webdemo.weapi.action.domain.ResponsePageVO;


import java.util.Map;

public interface AlarmLogService {


    ResponsePageVO selectAlarmByPage(Map<String, Object> param , ResponsePageVO responseData);


}
