package com.webdemo.weapi.action.domain.show.app;

import com.webdemo.weapi.action.domain.show.GpsLocation;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class TravelListShow {
    private String plateNumber;//车牌号
    private String deviceId;//设备代码
    private String travelCode;//实时行程唯一标识(32位UUID)
    private Integer	onlineState;//车辆在线状态（1在线，2不在线，3停车，4行驶）
    private Long 	tvavelId;//行程id
    private Long	travelStartTime;// 行程的开始时间
    private Long	travelEndTime;//行程结束时间
    private Double	mileage;//行程的行驶里程
    private Double	fuel;//驱动行程的燃料
    private Long	driverTime;//行程的开车时间（单位为：秒）
    private Double	avgSpeed;//平均整体出行速度
    private Float	maxSpeed;//最大速度
    private Float	maxRPM;//最大转速

    private Double	avgFuel;//每百公里油耗

    private Double	idleFuel;//行程空转报警总燃料 - 怠速油耗

    private Integer overSpeedTimes;//跳闸超速报警总数
    private Integer accelerateTimes;//跳闸加速报警总数
    private Integer decelerateTimes;//行程减速报警总数
    private Integer sharpTurnTimes;//行程急转弯警报总数
    private Integer fatigueTimes;//行程疲劳驾驶警报总数
    private Integer IdleTimes;//行程空转报警总数 - 怠速

    private Double	historicalTotalMileage;//总里程，单位是米或公里  - 累计 -暂无
    private Double	historicalTotalFuelConsumption;//总油耗，单位是L或者加仑  - 累计 -暂无
    private Long	historicalTotalDrivingTime;//累计运行时间 -暂无

    private GpsLocation startGpsLocation;
    private GpsLocation endGpsLocation;
}
