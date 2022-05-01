package com.webdemo.weapi.action.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WlDevice  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long deviceId;

    private String deviceCode;

    private Integer enterpriseId;

    private String operatorCode;

    private String userCode;

    private String vehicleCode;

    private String factoryDate;

    private String installDate;

    private String assetsCoding;

    private String assetsBarcode;

    private String simOperator;

    private String simCode;

    private String simValiddate;

    private String serialCode;

    private String deviceModel;

    private Integer deviceType;

    private Integer bindstatus;

    private String macActiveTime;

    private String version;

    private String imsi;

    private Integer validState;

    private String userBelongs;

    private String iccid;

    private Double fuelCorrect;

    private Integer adaptationStatus;

    private String desKey;

    private String createDate;

    private String updateDate;

    private String ssid;

    private String password;

    private String bindingTime;

    private String softwareVersion;

    private String hardwareVersion;

    private String manufacturer;

    private Integer loginStatus;

    private String modemSoftware;

    private String modemHardware;
}
