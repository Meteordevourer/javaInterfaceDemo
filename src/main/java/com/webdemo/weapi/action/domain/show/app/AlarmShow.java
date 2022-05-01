package com.webdemo.weapi.action.domain.show.app;

import com.webdemo.weapi.action.domain.show.GpsLocation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AlarmShow {
    private Integer dataType;//0: 全部告警1：点熄火告警2：驾驶告警3：一般告警...
    private String alarmId;//告警id
    private String alarmName;//告警名称
    private Long alarmTime;//告警时间， yyyy-MM-ddhh：mm：ss   -- machine_time
    private Float alarmDistance;//告警里程（单位：M）
    private String alarmPrivateValue;//警报私有值以 json


    private GpsLocation startGpsLocation;
    private GpsLocation endGpsLocation;

//    private String  startGpsLocation;//告警开始位置地址
//    private Float 	startHeight;//高度
//    private Float 	startHeading;//方向
//    private Integer	startLongitudeType;//告警开始 经度型（0东经，1西经）
//    private Integer	startLatitudeType;//告警开始 纬度类型（0南纬，1北纬）
//    private Integer	startSatellitesNumber;//告警开始 卫星数
//    private String	startGpsTime;//告警开始时间
//
//
//    private String  endGpsLocation;//告警结束位置

//    `speed_before` decimal(10,2) DEFAULT NULL COMMENT '加速或者减速之前的速度 单位km/h',
//    `speed_after` decimal(10,2) DEFAULT NULL COMMENT '加速或者减速之后的速度 单位km/h',
//    `speed_value` decimal(10,2) DEFAULT NULL COMMENT '加速度/减速度值',
//
//    reserved1` varchar(200) DEFAULT NULL COMMENT '附件内容1',
//    `reserved2` varchar(200) DEFAULT NULL COMMENT '附件内容2',
//    `reserved3` varchar(200) DEFAULT NULL COMMENT '附件内容3',
//
//    alarmDistance	Float	Y	alarms
//
//    alarmPrivateValue	String	N	alarms	警报私有值以 json 格式表示 demo:
//    {value1:20,value2:40,value3:4}
//
//    startGpsLocation	Object	Y	alarms	告警开始位置
//    gpsSpeed	Float	Y	startGpsLocation	GPS速度
//    latitude	Float	Y	startGpsLocation	纬度
//    longitude	Float	Y	startGpsLocation	经度
//    height	Float	Y	startGpsLocation	高度
//    heading	Float	Y	startGpsLocation	方向
//    longitudeType	Int	Y	startGpsLocation	经度型（0东经，1西经）
//    latitudeType	Int	Y	startGpsLocation	纬度类型（0南纬，1北纬）
//    satellitesNumber	Int	Y	startGpsLocation	卫星数
//    gpsTime	String	Y	startGpsLocation	时间
//    addressText	String	Y	startGpsLocation	地址
}
