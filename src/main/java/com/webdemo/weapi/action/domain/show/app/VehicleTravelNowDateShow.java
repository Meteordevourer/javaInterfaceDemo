package com.webdemo.weapi.action.domain.show.app;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VehicleTravelNowDateShow {

    private String plateNumber;//车牌号
    private String deviceId;//设备代码

    private Double mileage;
    private Double	fuel;
    private Integer	driverTime;
    private Double	avgSpeed;

}
