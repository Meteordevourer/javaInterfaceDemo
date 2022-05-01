package com.webdemo.weapi.action.controller;


import com.alibaba.fastjson.JSON;
import com.webdemo.weapi.action.domain.AlarmLog;
import com.webdemo.weapi.action.domain.ApiConconts;
import com.webdemo.weapi.action.domain.ResponsePageVO;
import com.webdemo.weapi.action.domain.TokenInfo;
import com.webdemo.weapi.action.domain.show.app.AlarmShow2;
import com.webdemo.weapi.action.service.AlarmLogService;
import com.webdemo.weapi.action.service.ApiInnerService;
import com.webdemo.weapi.utils.GsonUtil;
import com.webdemo.weapi.utils.lang.StringBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController {

    @Autowired
    private ApiInnerService apiInnerService;

    @Autowired
    private AlarmLogService alarmLogService;


    /**
     * 6.1 查询车辆告警信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/alarmList")
    public ResponsePageVO responsePageVO(HttpServletRequest request, HttpServletResponse response){
        ResponsePageVO responseData = new ResponsePageVO();
        Map<String,String> params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request,response,params,responseData);
        if(!flag.isCheck()){
            return responseData;
        }
        log.debug("wl-app-api::alarm - alarmList, 接口入参:{}", GsonUtil.getInstance().toJson(params));

        Map<String,Object> maps = (Map<String, Object>) JSON.parse(params.get("data"));
        String number = maps.get("plateNumber").toString();
        String[] numbers = number.split(",");
        String dataType = maps.get("dataType").toString();

        Map<String,Object> param = new HashMap<String, Object>();

        param.put("operatorCode",flag.getEmployee().getOperatorCode());
        if(StringBase.checkStr(dataType) && !"0".equals(dataType)){
            param.put("dataType",dataType);
        }
        if (StringBase.checkStr(number)){
            param.put("item",(number != null && 0 < numbers.length) ? Arrays.asList(numbers) : null);
        }
        param = apiInnerService.paramToPage(param,params);
        responseData = alarmLogService.selectAlarmByPage(param, responseData);

        List<AlarmLog> resList = (List<AlarmLog>) responseData.getData();
        List<AlarmShow2> showList = new ArrayList<AlarmShow2>();

        if (StringBase.checkArray(resList)) {
            for (AlarmLog res : resList) {
                showList.add(apiInnerService.alarmEntityToAlarmShow2(res));
            }
        }

        responseData.setData(showList);
        responseData.setResCode(ApiConconts.OK.toString());

        return responseData;

    }

}
