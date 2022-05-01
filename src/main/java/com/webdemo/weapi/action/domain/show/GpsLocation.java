package com.webdemo.weapi.action.domain.show;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GpsLocation implements Serializable {
    private static final long serialVersionUID = 1L;

    private Float gpsSpeed;//GPS速度
    private Double latitude;//纬度
    private Double longitude;//经度
    private Float height;//高度
    private Float heading;//方向
    private Integer longitudeType;//经度型（0东经，1西经）
    private Integer latitudeType;//纬度类型（0南纬，1北纬）
    private Integer satellitesNumber;//卫星数
    private Long gpsTime;//	时间
    private String addressText;//地址
}
