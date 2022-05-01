package com.webdemo.weapi.action.service.impl;

import com.webdemo.weapi.action.dao.TravelMapper;
import com.webdemo.weapi.action.domain.ApiConconts;
import com.webdemo.weapi.action.domain.ResponseDataListVO;
import com.webdemo.weapi.action.domain.ResponsePageVO;
import com.webdemo.weapi.action.domain.WlVehicleTravel;
import com.webdemo.weapi.action.domain.show.app.VehicleTravelNowDateShow;
import com.webdemo.weapi.action.domain.show.app.VehicleTravelShow;
import com.webdemo.weapi.action.service.ITravelService;
import com.webdemo.weapi.utils.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * @Description 查询车辆行程相关数据 5.x
 * @author dpf
 */

@Slf4j
@Service
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelMapper travelMapper;

    @Override
    public ResponseDataListVO selectVehicleTravelList(Map<String, Object> param, ResponseDataListVO responseData) {

        List<VehicleTravelShow> resList = travelMapper.selectVehicleTravelList(param);
        responseData.setData(resList);

        return responseData;
    }

    @Override
    public ResponsePageVO selectVehicleTravelInfoList(Map<String, Object> param, ResponsePageVO responseData) {
        long count = travelMapper.selectVehicleTravelInfoListCount(param);
        param = Pager.getParamPage(param,count,Integer.parseInt(param.get(ApiConconts.PAGE_SIZE).toString()));

        List<WlVehicleTravel> resList = travelMapper.selectVehicleTravelInfoList(param);

        responseData = responseData.getList(
                responseData,
                count,
                resList
        );


        return responseData;
    }


    /***
     * 查询今日行程汇总
     */

    @Override
    public ResponsePageVO selectVehicleTravelNowDateList(Map<String, Object> param, ResponsePageVO responseData) {

        long count = travelMapper.selectVehicleTravelNowDateListCount(param);
        param = Pager.getParamPage(param,count,Integer.parseInt(param.get(ApiConconts.PAGE_SIZE).toString()));

        List<VehicleTravelNowDateShow> resList = travelMapper.selectVehicleTravelNowDateList(param);

        responseData = responseData.getList(
                responseData,
                count,
                resList
        );

        return responseData;
    }

    @Override
    public WlVehicleTravel selectByTravelId(Map<String, Object> param) {
        return travelMapper.selectByTravelId(param);
    }

    @Override
    public int updateTravelMax(WlVehicleTravel param) {
        return travelMapper.updateTravelMax(param);
    }

    @Override
    public ResponsePageVO selectVehicleTravelListByDate(Map<String, Object> param, ResponsePageVO responseData) {
        long count = travelMapper.selectVehicleTravelListByDateCount(param);
        param = Pager.getParamPage(param,count,Integer.parseInt(param.get(ApiConconts.PAGE_SIZE).toString()));

        List<WlVehicleTravel> resList = travelMapper.selectVehicleTravelListByDate(param);

        responseData = responseData.getList(
                responseData,
                count,
                resList
        );


        return responseData;
    }
}
