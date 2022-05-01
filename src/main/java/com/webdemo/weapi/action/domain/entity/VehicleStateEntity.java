package com.webdemo.weapi.action.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class VehicleStateEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private String deviceCode;

    private String vehicleCode;

    private String userCode;

    private String operatorCode;

    private Integer enterpriseId;

    private Integer acc;

    private Integer attendState;

    private Integer onlineState;

    private Integer drivingState;

    private Integer operatingState;

    private String operatingDes;

    private Date updateTime;

    private Date deviceWakeUpTime;



    public Date getDeviceWakeUpTime() {
        return deviceWakeUpTime;
    }

    public void setDeviceWakeUpTime(Date deviceWakeUpTime) {
        this.deviceWakeUpTime = deviceWakeUpTime;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode == null ? null : vehicleCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getAcc() {
        return acc;
    }

    public void setAcc(Integer acc) {
        this.acc = acc;
    }

    public Integer getAttendState() {
        return attendState;
    }

    public void setAttendState(Integer attendState) {
        this.attendState = attendState;
    }

    public Integer getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    public Integer getDrivingState() {
        return drivingState;
    }

    public void setDrivingState(Integer drivingState) {
        this.drivingState = drivingState;
    }

    public Integer getOperatingState() {
        return operatingState;
    }

    public void setOperatingState(Integer operatingState) {
        this.operatingState = operatingState;
    }

    public String getOperatingDes() {
        return operatingDes;
    }

    public void setOperatingDes(String operatingDes) {
        this.operatingDes = operatingDes == null ? null : operatingDes.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
