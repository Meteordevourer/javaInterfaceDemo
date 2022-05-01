package com.webdemo.weapi.action.controller;

import com.alibaba.fastjson.JSON;
import com.webdemo.weapi.action.dao.VehicleStateMapper;
import com.webdemo.weapi.action.dao.WlDeviceMapper;
import com.webdemo.weapi.action.dao.WlVehicleMapper;
import com.webdemo.weapi.action.domain.*;
import com.webdemo.weapi.action.domain.entity.UWlDevice;
import com.webdemo.weapi.action.domain.entity.VehicleStateEntity;
import com.webdemo.weapi.action.domain.entity.WlVehicle;
import com.webdemo.weapi.action.domain.show.app.VehicleInfoShow;
import com.webdemo.weapi.action.domain.show.app.VehicleInfoShow2;
import com.webdemo.weapi.action.service.ApiInnerService;
import com.webdemo.weapi.action.service.DeviceService;
import com.webdemo.weapi.action.service.IVehicleService;
import com.webdemo.weapi.utils.DateUtil;
import com.webdemo.weapi.utils.GsonUtil;
import com.webdemo.weapi.utils.StringBaseUtils;
import com.webdemo.weapi.utils.lang.StringBase;
import com.zte.conn.data.base.MyDataOperations;
import com.zte.conn.parser.base.protobuf.GpsData;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.data.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private ApiInnerService apiInnerService;

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private MyDataOperations myDataOperations;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private WlVehicleMapper wlVehicleMapper;

    @Autowired
    private WlDeviceMapper wlDeviceMapper;

    @Autowired
    private VehicleStateMapper vehicleStateMapper;

    /***
     * 车辆信息接口
     *      4.1
     *
     * @param request   请求数据
     * @param response  响应数据
     */
    @PostMapping(value = "/vehicleInfoList")
    public ResponsePageVO vehicleInfoList(HttpServletRequest request, HttpServletResponse response) {

        ResponsePageVO responseData = new ResponsePageVO();
        Map<String, String> params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request, response, params, responseData);

        if (!flag.isCheck()) {

            return responseData;
        }
        log.debug("wl-app-api::vehicle-vehicleInfoList, 接口入参:{}", GsonUtil.getInstance().toJson(params));

        try {

            Map<String, String> maps = (Map<String, String>) JSON.parse(params.get("data"));
            String number = maps.get("plateNumber");
            String[] numbers = number.split(",");

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("operatorCode", flag.getEmployee().getOperatorCode());
            if(StringBase.checkStr(number)) {
                param.put("item", (numbers != null &&  0 < numbers.length) ? Arrays.asList(numbers) : null);
            }
            param = apiInnerService.paramToPage(param, params);

            responseData = vehicleService.selectVehicleList(param, responseData);

            responseData = setGps(responseData, flag);

            responseData.setResCode(ApiConconts.OK.toString());

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setResCode(ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString());
        }

        return responseData;

    }

    /**
     * 4.4 绑定车辆
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value =  "/vehicleBind")
    public ResponseDataListVO vehicleBind(HttpServletRequest request,HttpServletResponse response){
        ResponseDataListVO responseData = new ResponseDataListVO();

        try{
            Map<String, String> params = new HashMap<String, String>();
            TokenInfo flag = apiInnerService.handlerParamsData(request, response, params, responseData);

            if (!flag.isCheck()) {

                return responseData;
            }
            log.debug("wl-app-api::vehicle - vehiclebind, 接口入参:{}", GsonUtil.getInstance().toJson(params));

            Map<String, String> maps = (Map<String, String>) JSON.parse(params.get("data"));

            String deviceId = maps.get("deviceId");
            String plateNumber = maps.get("plateNumber");
            String vin = maps.get("vin");
            String brand = maps.get("brand");
            String series = maps.get("series");


            Map<String, Object> param = new HashMap<String, Object>();
            param.put("operatorCode", flag.getEmployee().getOperatorCode());
            param.put("deviceId", deviceId);


            //先查询其设备是否在当前机构中
            WlDevice device = deviceService.selectDeviceIsExists(param);

            if (null != device) {

                // 车机绑定状态(0未绑定；1绑定中；2已解绑)
                if (1 == device.getBindstatus()) {

                    responseData.setResCode(ApiConconts.ACTION_ERROR.toString());
                    responseData.setResMsg(ApiConconts.errorInfoMap.get(ApiConconts.ACTION_ERROR) + "::设备已被绑定");
                    return responseData;
                }

            } else {

                responseData.setResCode(ApiConconts.RESULTS_EMPTY.toString());
                responseData.setResMsg(ApiConconts.errorInfoMap.get(ApiConconts.RESULTS_EMPTY) + "::无法找到相应的设备");
                return responseData;
            }

            //查询车牌号码或vin是否已存在
            param.put("plateNumber", plateNumber);
            param.put("vin", vin);
            long is = vehicleService.selectVehicleIsExists(param);

            if(is > 0) {

                responseData.setResCode(ApiConconts.ACTION_ERROR.toString());
                responseData.setResMsg(ApiConconts.errorInfoMap.get(ApiConconts.ACTION_ERROR)+"::车牌或VIN码已存在");

                return responseData;
            }

            //录入车辆表
            int brandId = 0, seriesId = 0;
            try {
                brandId = Integer.parseInt(brand);
            } catch (Exception e) {}

            try {
                seriesId = Integer.parseInt(series);
            } catch (Exception e) {}


            String vehicleCode = UUID.randomUUID().toString();

            WlVehicle vehicle = new WlVehicle();
            vehicle.setOperatorCode(flag.getEmployee().getOperatorCode());
            vehicle.setVehicleNumber(plateNumber);
            vehicle.setDeviceCode(deviceId);
            vehicle.setVin(vin);
            vehicle.setBrandId(brandId);
            vehicle.setSeriesId(seriesId);
            vehicle.setVehicleCode(vehicleCode);
            vehicle.setEnterpriseId(flag.getEmployee().getEnterpriseId());

            wlVehicleMapper.insertSelective(vehicle);

            //更新设备表
            UWlDevice device_obj = new UWlDevice();
            device_obj.setDeviceId(device.getDeviceId());
            device_obj.setVehicleCode(vehicleCode);
            device_obj.setBindingTime(new Date());
            device_obj.setBindstatus(1);
            wlDeviceMapper.updateByPrimaryKeySelective(device_obj);


            //先查询车辆状态表是否已存在绑定的设备记录
            VehicleStateEntity deviceState = vehicleStateMapper.selectByPrimaryKey(device.getDeviceCode());

            VehicleStateEntity state = new VehicleStateEntity();
            state.setVehicleCode(vehicleCode);
            state.setDeviceCode(device.getDeviceCode());
            state.setOperatorCode(flag.getEmployee().getOperatorCode());
            state.setEnterpriseId(flag.getEmployee().getEnterpriseId());
            state.setDeviceWakeUpTime(new Date());

            if(null != deviceState) {

                //更新状态表
                state.setAcc(2);//车辆ACC：1点火，2熄火
                state.setAttendState(1);//出勤状态：1空闲，2出勤
                state.setOnlineState(1);//在线状态：1离线，2在线
                state.setDrivingState(2);//行驶状态：1行驶中(怠速事件结束)，2停留中(怠速事件开启)
                state.setOperatingState(1);//车辆运营状态：1正常， 2停运，3维修， 4故障，5维护
                state.setUpdateTime(new Date());
                vehicleStateMapper.updateByPrimaryKeySelective(state);

            }else {

                //录入车辆状态表
                vehicleStateMapper.insertSelective(state);
            }

            responseData.setResCode(ApiConconts.OK.toString());



        }catch (Exception e){
            log.error("wl-app-api::device - pustDeviceData, has exception:{}", e);
            e.printStackTrace();
            responseData.setResCode(ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString());
            responseData.setResMsg(ApiConconts.errorInfoMap.get(ApiConconts.INTERNAL_INTERFACE_ABNORMAL)+"::"+e.getMessage());
        }
        return responseData;
    }





    public ResponsePageVO setGps(ResponsePageVO responseData,TokenInfo flag) {

        List<VehicleInfoShow> resList = (List<VehicleInfoShow>) responseData.getData();
        List<VehicleInfoShow2> showList = new ArrayList<VehicleInfoShow2>();

        if (StringBase.checkArray(resList)) {

            log.debug("wl-app-api::vehicle-vehicleInfoList - setGps, 获取到数据共计:{}", resList.size());

            for (VehicleInfoShow res : resList) {

                VehicleInfoShow2 show = new VehicleInfoShow2();

                show.setDateAdded(res.getDateAdded() != null ? DateUtil.localToUTC_Time(res.getDateAdded()) : -9999);
                show.setPlateNumber(res.getPlateNumber() != null ? res.getPlateNumber() : "");
                show.setDeviceId(res.getDeviceId() != null ? res.getDeviceId() : "");

                show.setOnlineState(StringBaseUtils.vehicleState(res.getOnlineState() != null ? res.getOnlineState() : 255, res.getAcc() != null ? res.getAcc() : 255));

                show.setVehicleIdentificationNumber(res.getVehicleIdentificationNumber() != null ? res.getVehicleIdentificationNumber() : "");
                show.setVehicleManufacturers(res.getVehicleManufacturers() != null ? res.getVehicleManufacturers() : "");
                show.setSeries(res.getSeries() != null ? res.getSeries() : "");
                show.setVehicleMode(res.getVehicleMode() != null ? res.getVehicleMode() : "");
                show.setVehicleYear(res.getVehicleYear() != null ? res.getVehicleYear() : "");
                show.setPowerType(res.getPowerType() != null ? res.getPowerType() : 255);
                show.setFuelVolume(res.getFuelVolume() != null ? res.getFuelVolume() : -9999);
                show.setEngineType(res.getEngineType() != null ? res.getEngineType() : "");
                show.setVehicleColor(res.getVehicleColor() != null ? res.getVehicleColor() : "");
                show.setEngineCapacity(res.getEngineCapacity() != null ? res.getEngineCapacity() : -9999);
                show.setEmissionStandards(res.getEmissionStandards() != null ? res.getEmissionStandards() : "");

                if (StringBase.checkStr(res.getDeviceId())) {
                    // gps数据
                    GpsData.gpsData data = myDataOperations.getLastGpsData(res.getDeviceId());
                    if (data != null) {

                        show.setGpsLocation(apiInnerService.gpsDataToGpsLocation(data, flag, 1));

                    }
                }

                showList.add(show);
            }

            log.debug("wl-app-api::vehicle-vehicleInfoList - setGps  -- end , 所有的数据获取最后一个GPS结束");
        }else {

            log.debug("wl-app-api::vehicle-vehicleInfoList - setGps, 获取到数据共计:0 条");
        }

        responseData.setData(showList);

        return responseData;
    }
}
