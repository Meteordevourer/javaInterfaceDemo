package com.webdemo.weapi.action.domain.show.app;

import com.webdemo.weapi.action.domain.show.GpsLocation;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class VehicleTravelShow {
    private String plateNumber;//车牌号
    private String deviceId;//设备代码
    private String travelCode;//实时行程唯一标识(32位UUID)
    private Integer	onlineState;//车辆在线状态（1在线，2不在线，3停车，4行驶）
    private Integer	acc;
    private String	travelStartTime;// 行程的开始时间
    private String	travelEndTime;//行程结束时间
    private Integer	travleIsEnd;//实时行程是否结束(0未结束，1已结束)

    private List<GpsLocation> gpsLocations;


}
