package com.webdemo.weapi.action.domain.show.app;

import com.webdemo.weapi.action.domain.show.GpsLocation;
import lombok.Data;
import lombok.ToString;

/***
 * 车辆信息接口封装类
 * @author dpf  19/8/1
 */
@Data
@ToString
public class VehicleInfoShow {
    private String plateNumber;
    private String deviceId;
    private Integer onlineState;
    private Integer acc;
    private String VehicleIdentificationNumber;
    private String vehicleManufacturers;
    private String series;
    private String vehicleMode;
    private String vehicleYear;
    private Integer powerType;
    private Float fuelVolume;
    private String engineType;
    private String vehicleColor;
    private Float engineCapacity;
    private String emissionStandards;
    private String dateAdded;

    private GpsLocation gpsLocation;
}
