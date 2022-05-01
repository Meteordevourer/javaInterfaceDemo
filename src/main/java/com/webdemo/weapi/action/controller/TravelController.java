package com.webdemo.weapi.action.controller;


import com.alibaba.fastjson.JSON;
import com.webdemo.weapi.action.domain.*;

import com.webdemo.weapi.action.domain.show.GpsLocation;
import com.webdemo.weapi.action.domain.show.app.*;


import com.webdemo.weapi.action.service.ApiInnerService;
import com.webdemo.weapi.action.service.ITravelService;
import com.webdemo.weapi.utils.DateUtil;
import com.webdemo.weapi.utils.GsonUtil;
import com.webdemo.weapi.utils.StringBaseUtils;
import com.webdemo.weapi.utils.lang.StringBase;
import com.zte.conn.data.base.MyDataOperations;
import com.zte.conn.parser.base.protobuf.GpsData;
import com.zte.conn.parser.base.protobuf.ObdData;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private ApiInnerService apiInnerService;

    @Autowired
    private ITravelService travelService;

    @Autowired
    private MyDataOperations myDataOperations;



    /**
     * 5.1 查询车辆行程信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/travelList")
    public ResponsePageVO travelList(HttpServletRequest request, HttpServletResponse response){
        ResponsePageVO responseData = new ResponsePageVO();
        Map<String,String>params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request,response,params,responseData);

        if (!flag.isCheck()){
            return responseData;
        }
        log.debug("wl-app-api::travel-travelList,接口入参:{}", GsonUtil.getInstance().toJson(params));
        try {

            Map<String, String> maps = (Map<String, String>) JSON.parse(params.get("data"));
            String number = maps.get("plateNumber");
            String[] numbers = number.split(",");


            String travelType = maps.get("travelType"); //行程类型：（默认1）1.私人 2.商业
            String fuelType = maps.get("fuelType"); //油耗单位类型：（默认2）1.升 2.加仑
            String mileageType = maps.get("mileageType"); //里程单位类型：（默认2）1.公里/小时 2.米/小时
            String speedType = maps.get("speedType"); //速度单位类型：（默认2）1.公里/小时 2.米/小时


            Map<String, Object> param = new HashMap<String, Object>();
            param.put("operatorCode", flag.getEmployee().getOperatorCode());
            if(StringBase.checkStr(number)) {
                param.put("item", (numbers != null &&  0 < numbers.length) ? Arrays.asList(numbers) : null);
            }
            param.put("travelType", travelType);
            param.put("mileageType", mileageType);
            param.put("speedType", speedType);

            param = apiInnerService.paramToPage(param, params);

            responseData = travelService.selectVehicleTravelInfoList(param, responseData);

            responseData = setGpsAndAlarm_travel(responseData, flag, mileageType, speedType, fuelType);

            responseData.setResCode(ApiConconts.OK.toString());

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setResCode(ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString());
        }

        return responseData;
    }


    /**
     * 5.2	查询车辆行程轨迹信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/travelInfo")
    public ResponseDataListVO travelInfo(HttpServletRequest request, HttpServletResponse response) {

        ResponseDataListVO responseData = new ResponseDataListVO();
        Map<String, String> params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request, response, params, responseData);

        if (!flag.isCheck()) {

            return responseData;
        }
        log.debug("wl-app-api::travel - travelInfo, 接口入参:{}", GsonUtil.getInstance().toJson(params));

        try {

            Map<String, String> maps = (Map<String, String>) JSON.parse(params.get("data"));
            String travelId = maps.get("travelId");

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("operatorCode", flag.getEmployee().getOperatorCode());
            param.put("travelId", travelId);


            Date one = new Date();
            System.out.println("\r\n=====travelInfo==============1."+DateUtil.dateToDateString(one));

            List<TravelInfoShow> showList = new ArrayList<TravelInfoShow>();
            WlVehicleTravel travelRes = travelService.selectByTravelId(param);

            Date two = new Date();
            System.out.println("=====travelInfo==============showList 2."+DateUtil.dateToDateString(two)+"    dec="+ (two.getTime() - one.getTime()) );

            if(null != travelRes) {

                TravelInfoShow show = setGpsAndAlarm_travelInfo(travelRes, flag);

                showList.add(show);

                responseData.setResCode(ApiConconts.OK.toString());

            }else {
                responseData.setResCode(ApiConconts.RESULTS_EMPTY.toString());
                responseData.setResMsg(ApiConconts.errorInfoMap.get(ApiConconts.RESULTS_EMPTY));
            }

            responseData.setData(showList);

            Date there = new Date();
            System.out.println("=====travelInfo==============3."+DateUtil.dateToDateString(two)+"    dec="+ (there.getTime() - two.getTime()) );

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setResCode(ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString());
        }


        return responseData;
    }

    /**
     * 5.3 查询车辆实时轨迹信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/travelPath")
    ResponseDataListVO travelPath(HttpServletRequest request,HttpServletResponse response){
        ResponseDataListVO responseData = new ResponseDataListVO();
        Map<String,String> params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request,response,params,responseData);

        if (!flag.isCheck()){
            return responseData;
        }

        log.debug("wl-app-api::travel-travelPath,接口入参:{}",GsonUtil.getInstance().toJson(params));

        try{
            Map<String,String> maps = (Map<String, String>)JSON.parse(params.get("data"));
            String number = maps.get("plateNumber");
            String[] numbers = number.split(",");

            Map<String,Object> param = new HashMap<String, Object>();
            param.put("operatorCode",flag.getEmployee().getOperatorCode());
            if (StringBase.checkStr(number)){
                param.put("item",(number != null && 0 < numbers.length)?Arrays.asList(numbers):null);
            }
            param = apiInnerService.paramToPage(param,params);

            responseData = travelService.selectVehicleTravelList(param,responseData);

            responseData = setGpsAndAlarm_travelPath(responseData,flag);
            responseData.setResCode(ApiConconts.OK.toString());
        }catch (Exception e){
            e.printStackTrace();
            responseData.setResCode(ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString());
        }
        log.info("wl-app-api::travel - travelPath, 返回值打印:{}",  GsonUtil.getInstance().toJson(responseData));
        return responseData;
    }


    /**
     *  5.4 查询车辆今日行程汇总
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/travelNowDate")
    ResponsePageVO travelNowDate(HttpServletRequest request,HttpServletResponse response){
        ResponsePageVO responseData = new ResponsePageVO();
        Map<String,String> params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request,response,params,responseData);
        if (!flag.isCheck()){
            return responseData;
        }
        log.debug("wl-app-api::travel-travelNowDate,接口入参:{}",GsonUtil.getInstance().toJson(params));

        try {
            Map<String,String> maps = (Map<String, String>) JSON.parse(params.get("data"));
            String number = maps.get("plateNumber");
            String[] numbers = number.split(",");

            Map<String,Object> param = new HashMap<String, Object>();
            param.put("operatorCode",flag.getEmployee().getOperatorCode());
            if(StringBase.checkStr(number)){
                param.put("item",(number!=null && 0<numbers.length)?Arrays.asList(numbers):null);
            }

            String nowDate = DateUtil.convertDateToString(new Date(),DateUtil.DATE_FORMAT);
            param.put("nowDate",nowDate);

            param = apiInnerService.paramToPage(param,params);

            responseData = travelService.selectVehicleTravelNowDateList(param,responseData);

            responseData.setResCode(ApiConconts.OK.toString());

        }catch (Exception e){
            e.printStackTrace();
            responseData.setResCode(ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString());
        }

        return responseData;
    }


    /**
     *    5.5 按日期查询车辆行程信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/travelDateList")
    ResponsePageVO travelDateList(HttpServletRequest request,HttpServletResponse response){
        ResponsePageVO responseData = new ResponsePageVO();
        Map<String,String> params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request,response,params,responseData);

        if (!flag.isCheck()){
            return responseData;
        }
        log.debug("wl-app-api::travel-travelDateList,接口入参:{}", GsonUtil.getInstance().toJson(params));
        try {

            Map<String, String> maps = (Map<String, String>) JSON.parse(params.get("data"));
            String number = maps.get("plateNumber");
            String[] numbers = number.split(",");


            String travelType = maps.get("travelType"); //行程类型：（默认1）1.私人 2.商业
            String fuelType = maps.get("fuelType"); //油耗单位类型：（默认2）1.升 2.加仑
            String mileageType = maps.get("mileageType"); //里程单位类型：（默认2）1.公里/小时 2.米/小时
            String speedType = maps.get("speedType"); //速度单位类型：（默认2）1.公里/小时 2.米/小时
            String thisDate = maps.get("date");//日期单位类型：yyyy-mm-dd  String


            Map<String, Object> param = new HashMap<String, Object>();
            param.put("operatorCode", flag.getEmployee().getOperatorCode());
            if(StringBase.checkStr(number)) {
                param.put("item", (numbers != null &&  0 < numbers.length) ? Arrays.asList(numbers) : null);
            }
            param.put("travelType", travelType);
            param.put("mileageType", mileageType);
            param.put("speedType", speedType);

            param.put("thisDate",thisDate);
            log.debug("wl-app-api::travel-travelDateList,接口入参:{}", GsonUtil.getInstance().toJson(params));


            param = apiInnerService.paramToPage(param, params);

            responseData = travelService.selectVehicleTravelListByDate(param, responseData);

            responseData = setGpsAndAlarm_travel(responseData, flag, mileageType, speedType, fuelType);

            responseData.setResCode(ApiConconts.OK.toString());

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setResCode(ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString());
        }
        return responseData;
    }






    public ResponsePageVO setGpsAndAlarm_travel(ResponsePageVO responseData,TokenInfo flag,
                                                String mileageType, String speedType, String fuelType) {

        List<WlVehicleTravel> resList = (List<WlVehicleTravel>) responseData.getData();
        List<TravelListShow> showList = new ArrayList<TravelListShow>();
        if (StringBase.checkArray(resList)) {

            //fuelType油耗单位类型：（默认2）1.升 2.加仑
            //mileageType里程单位类型：（默认2）1.公里/小时 2.米/小时
            //speedType速度单位类型：（默认2）1.公里/小时  2.米/小时

            for (WlVehicleTravel res : resList) {

                TravelListShow show = new TravelListShow();

                show.setPlateNumber(res.getVehicleNumber() != null ? res.getVehicleNumber() : "");//车牌号
                show.setDeviceId(res.getDeviceCode() != null ? res.getDeviceCode() : "");//设备代码
                show.setTravelCode(res.getTravelCode() != null ? res.getTravelCode() : "");//实时行程唯一标识(32位UUID)
                show.setOnlineState(StringBaseUtils.vehicleState(res.getOnlineState() != null ? res.getOnlineState() : 255, res.getAcc() != null ? res.getAcc() : 255));//车辆在线状态
                show.setTvavelId(res.getTravelId() != null ? res.getTravelId() : -9999);//行程id
                show.setTravelStartTime(res.getStartTime() != null ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(res.getStartTime())) : -9999);// 行程的开始时间
                show.setTravelEndTime(res.getEndTime() != null ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(res.getEndTime())) : -9999);//行程结束时间
                show.setMileage(res.getMileage()  != null ? StringBase.kmToM(res.getMileage().doubleValue(), mileageType) : -9999);//行程的行驶里程
                show.setFuel(res.getFuel()  != null ? StringBase.ltoUsGul(res.getFuel().doubleValue(), fuelType) : -9999);//驱动行程的燃料
                show.setDriverTime(res.getDriverTime() != null ? (long)(res.getDriverTime().doubleValue() * 60 * 60) : -9999);//行程的开车时间（单位为：秒）
                show.setAvgSpeed(res.getAvgSpeed() != null ? StringBase.kmToM_Speed(res.getAvgSpeed().doubleValue(), speedType) : -9999);//平均整体出行速度

                show.setAvgFuel(res.getAvgFuel() != null ? StringBase.ltoUsGul(res.getAvgFuel().doubleValue(), fuelType) : -9999);//每百公里油耗
                show.setIdleFuel(res.getLazySpeedFuel() != null ? StringBase.ltoUsGul(res.getLazySpeedFuel().doubleValue(), fuelType) : -9999);//行程空转报警总燃料 - 怠速油耗
                show.setOverSpeedTimes(res.getOverspeedTimes() != null ? res.getOverspeedTimes().intValue() : -9999);//跳闸超速报警总数
                show.setAccelerateTimes(res.getAccelerateTimes() != null ? res.getAccelerateTimes().intValue() : -9999);//跳闸加速报警总数
                show.setDecelerateTimes(res.getDecelerateTimes() != null ? res.getDecelerateTimes().intValue() : -9999);//行程减速报警总数
                show.setSharpTurnTimes(res.getSharpTurnTimes() != null ? res.getSharpTurnTimes().intValue() : -9999);//行程急转弯警报总数
                show.setFatigueTimes(res.getFatigueTimes() != null ? res.getFatigueTimes().intValue() : -9999);//行程疲劳驾驶警报总数
                show.setIdleTimes(res.getLazySpeedTimes() != null ? res.getLazySpeedTimes().intValue() : -9999);//行程空转报警总数 - 怠速

                show.setHistoricalTotalMileage(-9999D);//总里程，单位是米或公里  - 累计 -暂无
                show.setHistoricalTotalFuelConsumption(-9999D);//总油耗，单位是L或者加仑  - 累计 -暂无
                show.setHistoricalTotalDrivingTime(-9999L);//累计运行时间 -暂无

                GpsLocation startGpsLocation = new GpsLocation();
                startGpsLocation.setLatitude(res.getStartLatitude() != null ? res.getStartLatitude().doubleValue() : -9999);//纬度
                startGpsLocation.setLongitude(res.getStartLongitude() != null ? res.getStartLongitude().doubleValue() : -9999);//经度
                startGpsLocation.setGpsSpeed(-9999F);//GPS速度
                startGpsLocation.setHeight(-9999F);//高度
                startGpsLocation.setHeading(-9999F);//方向
                startGpsLocation.setLongitudeType(-9999);//经度型（0东经，1西经）
                startGpsLocation.setLatitudeType(-9999);//纬度类型（0南纬，1北纬）
                startGpsLocation.setSatellitesNumber(-9999);//卫星数
                startGpsLocation.setGpsTime(show.getTravelStartTime());//	时间
                startGpsLocation.setAddressText(res.getStartAddress() != null ? res.getStartAddress() : "");//地址
                show.setStartGpsLocation(startGpsLocation);

                GpsLocation endGpsLocation = new GpsLocation();
                endGpsLocation.setLatitude(res.getEndLatitude() != null ? res.getEndLatitude().doubleValue() : -9999);//纬度
                endGpsLocation.setLongitude(res.getEndLongitude() != null ? res.getEndLongitude().doubleValue() : -9999);//经度
                endGpsLocation.setGpsSpeed(-9999F);//GPS速度
                endGpsLocation.setHeight(-9999F);//高度
                endGpsLocation.setHeading(-9999F);//方向
                endGpsLocation.setLongitudeType(-9999);//经度型（0东经，1西经）
                endGpsLocation.setLatitudeType(-9999);//纬度类型（0南纬，1北纬）
                endGpsLocation.setSatellitesNumber(-9999);//卫星数
                endGpsLocation.setGpsTime(show.getTravelEndTime());//	时间
                endGpsLocation.setAddressText(res.getEndAddress() != null ? res.getEndAddress() : "");//地址
                show.setEndGpsLocation(endGpsLocation);


                //如果表里面没有这个行程的maxSpeed和maxRPM
                if(null == res.getMaxSpeed() || null == res.getMaxRpm()) {

                    float engineSpeed = -9999;
                    float carSpeed = -9999;

                    List<ObdData.obdData> obdList = myDataOperations.getPageObdData(show.getDeviceId(), show.getTravelStartTime(), show.getTravelEndTime(), Integer.MAX_VALUE);

                    if(StringBase.checkArray(obdList)) {

                        for (ObdData.obdData data : obdList) {

                            if(engineSpeed < data.getEngineSpeed()) {
                                engineSpeed = data.getEngineSpeed();
                            }

                            if(carSpeed < data.getCarSpeed()) {
                                carSpeed = data.getCarSpeed();
                            }

                        }
                    }else {

                        List<GpsData.gpsData> gpsList= myDataOperations.getPageGpsDatas(show.getDeviceId(),
                                show.getTravelStartTime(), show.getTravelEndTime(),
                                Integer.MAX_VALUE, 1);

                        if(StringBase.checkArray(gpsList)) {

                            for (GpsData.gpsData data : gpsList) {

                                if(carSpeed < data.getGpsSpeed()) {
                                    carSpeed = data.getGpsSpeed();
                                }

                            }

                        }

                    }

                    show.setMaxSpeed(StringBase.kmToM_Speed(carSpeed, speedType));
                    show.setMaxRPM(engineSpeed);

                    //更新一下表的数据，方便后面不用再去循环
                    res.setMaxRpm(BigDecimal.valueOf(engineSpeed));
                    res.setMaxSpeed(BigDecimal.valueOf(carSpeed));
                    travelService.updateTravelMax(res);
                }else {
                    show.setMaxSpeed(StringBase.kmToM_Speed(res.getMaxSpeed().floatValue(), speedType));
                    show.setMaxRPM(res.getMaxRpm().floatValue()  );
                }

                showList.add(show);
            }

        }


        responseData.setData(showList);

        return responseData;
    }
    public TravelInfoShow  setGpsAndAlarm_travelInfo(WlVehicleTravel travelRes,TokenInfo flag) {



        TravelInfoShow show = new TravelInfoShow();

        show.setPlateNumber(travelRes.getVehicleNumber() != null ? travelRes.getVehicleNumber() : "");//车牌号
        show.setDeviceId(travelRes.getDeviceCode() != null ? travelRes.getDeviceCode() : "");//设备代码
        show.setTravelCode(travelRes.getTravelCode() != null ? travelRes.getTravelCode() : "");//实时行程唯一标识(32位UUID)
        show.setOnlineState(StringBaseUtils.vehicleState(travelRes.getOnlineState() != null ? travelRes.getOnlineState() : 255, travelRes.getAcc() != null ? travelRes.getAcc() : 255));//车辆在线状态
        show.setTvavelId(travelRes.getTravelId() != null ? travelRes.getTravelId() : -9999);//行程id
        show.setTravelStartTime(travelRes.getStartTime() != null ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(travelRes.getStartTime())) : -9999);// 行程的开始时间
        show.setTravelEndTime(travelRes.getEndTime() != null ? DateUtil.localToUTC_Time(DateUtil.dateToDateString(travelRes.getEndTime())) : -9999);//行程结束时间


        List<GpsLocation> gpsLocations = new ArrayList<GpsLocation>();

        if(null != show.getTravelStartTime()) {

            String travelEndTime = travelRes.getEndTime() != null ? DateUtil.dateToDateString(travelRes.getEndTime()) : null ;

            try {

                if(!StringBase.checkStr(travelEndTime)) {

                    travelEndTime = DateUtil.addMonth(DateUtil.convertDateToDate(new Date(show.getTravelStartTime()), DateUtil.TIMEF_FORMAT),
                            1, DateUtil.TIMEF_FORMAT);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            //组合行程的GPS点
            if(StringBase.checkStr(show.getDeviceId())) {

                try {

                    Date _1 = new Date();

                    List<GpsData.gpsData> gpsList = myDataOperations.getPageGpsDatas(show.getDeviceId(),
                            show.getTravelStartTime(),
                            DateUtil.convertStringToDate(travelEndTime, DateUtil.TIMEF_FORMAT).getTime(), Integer.MAX_VALUE, 1);

                    Date _2 = new Date();
                    System.out.println("=====travelInfo==============Gps_list 1."+DateUtil.dateToDateString(_2)+"    dec="+ (_2.getTime() - _1.getTime()) +"  listSzie="+gpsList.size() );

                    if(StringBase.checkArray(gpsList)) {

                        for (GpsData.gpsData data : gpsList) {

                            gpsLocations.add(apiInnerService.gpsDataToGpsLocation(data, flag, 0));
                        }
                    }

                    Date _3 = new Date();
                    System.out.println("=====travelInfo==============Gps_list 2."+DateUtil.dateToDateString(_3)+"    dec="+ (_3.getTime() - _2.getTime()));


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //组合行程的告警数据
            //travelRes.setVehicleNumber("WL_454215");

//            if(StringBase.checkStr(travelRes.getVehicleNumber())) {
//
//                Map<String, Object> alarm_param = new HashMap<String, Object>();
//                alarm_param.put("vehicleNumber", travelRes.getVehicleNumber());
//
//                try {
//                    alarm_param.put("startTime", DateUtil.ds(travelRes.getStartTime()));
//                    alarm_param.put("endTime", travelEndTime);
//
//                    Date _alarm_1 = new Date();
//
//                    List<AlarmLogEntity> alarmList = alarmLogService.selectListByVehicleCode(alarm_param);
//
//                    Date _alarm_2 = new Date();
//                    System.out.println("=====travelInfo==============Alarm_list 1."+DateUtil.dateToDateString(_alarm_2)+"    dec="+ (_alarm_2.getTime() - _alarm_1.getTime()) +"  listSzie="+alarmList.size() );
//
//                    if(StringBase.checkArray(alarmList)) {
//
//                        for (AlarmLogEntity alarm : alarmList) {
//
//                            asList.add(apiInnerService.alarmEntityToAlarmShow(alarm));
//                        }
//
//                    }
//
//                    Date _alarm_3 = new Date();
//                    System.out.println("=====travelInfo==============Alarm_list 2."+DateUtil.dateToDateString(_alarm_3)+"    dec="+ (_alarm_3.getTime() - _alarm_2.getTime()));
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }

        }

        show.setGpsLocations(gpsLocations);
    //    show.setAlarms(asList);

        return show;

    }
    public ResponseDataListVO setGpsAndAlarm_travelPath(ResponseDataListVO responseData,TokenInfo flag){
        List<VehicleTravelShow> resList = (List<VehicleTravelShow>) responseData.getData();
        List<VehicleTravelShow2> showList = new ArrayList<VehicleTravelShow2>();

        if(StringBase.checkArray(resList)){
            log.debug("wl-app-api::travel - travelPath - setGpsAndAlarm_travelPath, 获取到数据共计:{}", resList.size());

            for (VehicleTravelShow res : resList){

                VehicleTravelShow2 show = new VehicleTravelShow2();
                show.setPlateNumber(res.getPlateNumber() != null ? res.getPlateNumber() : "");//车牌号
                show.setDeviceId(res.getDeviceId() != null ? res.getDeviceId() : "");//设备代码
                show.setTravelCode(res.getTravelCode() != null ? res.getTravelCode() : "");//实时行程唯一标识(32位UUID)
                show.setOnlineState(StringBaseUtils.vehicleState(res.getOnlineState() != null ? res.getOnlineState() : 255, res.getAcc() != null ? res.getAcc() : 255));//车辆在线状态
                show.setTravelStartTime(res.getTravelStartTime() != null ? DateUtil.localToUTC_Time(res.getTravelStartTime()) : -9999);// 行程的开始时间
                show.setTravelEndTime(res.getTravelEndTime() != null ? DateUtil.localToUTC_Time(res.getTravelEndTime()) : -9999);//行程结束时间
                show.setTravleIsEnd(res.getTravleIsEnd() != null ? res.getTravleIsEnd() : 255);//实时行程是否结束(0未结束，1已结束)

                List<GpsLocation> gpsLocations = new ArrayList<GpsLocation>();


                if(StringBase.checkStr(res.getTravelStartTime())) {


                    res.setTravelStartTime(res.getTravelStartTime());

                    String travelEndTime = res.getTravelEndTime();

                    try {

                        if(!StringBase.checkStr(res.getTravelEndTime())) {

                            travelEndTime = DateUtil.addMonth(DateUtil.convertStringToDate(res.getTravelStartTime(), DateUtil.TIMEF_FORMAT),
                                    1, DateUtil.TIMEF_FORMAT);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //组合行程的GPS点
                    if (StringBase.checkStr(res.getDeviceId()) && StringBase.checkStr(res.getTravelStartTime())) {

                        try {

                            long endTime = new Date().getTime();
                            try {

                                if(res.getTravelEndTime() != null) {
                                    endTime = DateUtil.convertStringToDate(res.getTravelEndTime(), DateUtil.TIMEF_FORMAT).getTime();
                                }
                            } catch (Exception e) {

                            }



                            List<GpsData.gpsData> gpsList= myDataOperations.getPageGpsDatas(res.getDeviceId(),
                                    DateUtil.convertStringToDate(res.getTravelStartTime(), DateUtil.TIMEF_FORMAT).getTime(),
                                    endTime, Integer.MAX_VALUE, 1);

                            if(StringBase.checkArray(gpsList)) {

                                for (GpsData.gpsData data : gpsList) {

                                    gpsLocations.add(apiInnerService.gpsDataToGpsLocation(data, flag, 0));
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }//--- gps

            }
                show.setGpsLocations(gpsLocations);

                showList.add(show);

            }//end for

            log.debug("wl-app-api::travel - travelPath - setGpsAndAlarm_travelPath  -- end , 数据封装结束");

        }//end if
        else {

            log.debug("wl-app-api::travel - travelPath - setGpsAndAlarm_travelPath, 获取到数据共计:0 条 ");
        }

        responseData.setData(showList);

        return responseData;
        }
}


