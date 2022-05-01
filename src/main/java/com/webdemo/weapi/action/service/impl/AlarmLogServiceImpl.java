package com.webdemo.weapi.action.service.impl;

import com.webdemo.weapi.action.dao.AlarmLogMapper;
import com.webdemo.weapi.action.domain.AlarmLog;
import com.webdemo.weapi.action.domain.ApiConconts;
import com.webdemo.weapi.action.domain.ResponsePageVO;
import com.webdemo.weapi.action.service.AlarmLogService;
import com.webdemo.weapi.utils.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class AlarmLogServiceImpl  implements AlarmLogService {
    @Autowired
    private AlarmLogMapper alarmLogMapper;

    @Override
    public ResponsePageVO selectAlarmByPage(Map<String, Object> param, ResponsePageVO responseData) {
        long count = alarmLogMapper.selectAlarmByPageCount(param);
        param = Pager.getParamPage(param, count, Integer.parseInt(param.get(ApiConconts.PAGE_SIZE).toString()));

        List<AlarmLog> resList = alarmLogMapper.selectAlarmByPage(param);

        responseData = responseData.getList(
                responseData,
                count,
                resList);

        return responseData;
    }
}
