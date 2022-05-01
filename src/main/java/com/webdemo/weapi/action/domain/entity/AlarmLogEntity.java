package com.webdemo.weapi.action.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AlarmLogEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer enterpriseId;

    private String operatorCode;

    private String operatorName;

    private String vehicleCode;

    private String userCode;

    private String deviceCode;

    private String vehicleNumber;

    private String vin;

    private Integer modelId;

    private String modelName;

    private String travelCode;

    private String travelName;

    private Date startTime;

    private Date endTime;

    private BigDecimal startLongitude;

    private BigDecimal startLatitude;

    private BigDecimal endLongitude;

    private BigDecimal endLatitude;

    private String startLocation;

    private String endLocation;

    private String startLocationZh;

    private String endLocationZh;

    private String alarmId;

    private String scoreBuckleInfo;

    private BigDecimal scoreBuckle;

    private String alarmName;

    private Integer isAlarm;

    private BigDecimal alarmTime;

    private BigDecimal speedBefore;

    private BigDecimal speedAfter;

    private BigDecimal speedValue;

    private BigDecimal alarmDistance;

    private Date machineTime;

    private Date receiverTime;

    private Date logTime;

    private Integer isShow;

    private Integer isError;

    private String errorMsg;

    private String reserved1;

    private String reserved2;

    private String reserved3;



    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public Integer getIsError() {
        return isError;
    }

    public void setIsError(Integer isError) {
        this.isError = isError;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
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

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber == null ? null : vehicleNumber.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getTravelCode() {
        return travelCode;
    }

    public void setTravelCode(String travelCode) {
        this.travelCode = travelCode == null ? null : travelCode.trim();
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName == null ? null : travelName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(BigDecimal startLongitude) {
        this.startLongitude = startLongitude;
    }

    public BigDecimal getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(BigDecimal startLatitude) {
        this.startLatitude = startLatitude;
    }

    public BigDecimal getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(BigDecimal endLongitude) {
        this.endLongitude = endLongitude;
    }

    public BigDecimal getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(BigDecimal endLatitude) {
        this.endLatitude = endLatitude;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation == null ? null : startLocation.trim();
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation == null ? null : endLocation.trim();
    }

    public String getStartLocationZh() {
        return startLocationZh;
    }

    public void setStartLocationZh(String startLocationZh) {
        this.startLocationZh = startLocationZh == null ? null : startLocationZh.trim();
    }

    public String getEndLocationZh() {
        return endLocationZh;
    }

    public void setEndLocationZh(String endLocationZh) {
        this.endLocationZh = endLocationZh == null ? null : endLocationZh.trim();
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId == null ? null : alarmId.trim();
    }

    public String getScoreBuckleInfo() {
        return scoreBuckleInfo;
    }

    public void setScoreBuckleInfo(String scoreBuckleInfo) {
        this.scoreBuckleInfo = scoreBuckleInfo == null ? null : scoreBuckleInfo.trim();
    }

    public BigDecimal getScoreBuckle() {
        return scoreBuckle;
    }

    public void setScoreBuckle(BigDecimal scoreBuckle) {
        this.scoreBuckle = scoreBuckle;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }

    public Integer getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(Integer isAlarm) {
        this.isAlarm = isAlarm;
    }

    public BigDecimal getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(BigDecimal alarmTime) {
        this.alarmTime = alarmTime;
    }

    public BigDecimal getSpeedBefore() {
        return speedBefore;
    }

    public void setSpeedBefore(BigDecimal speedBefore) {
        this.speedBefore = speedBefore;
    }

    public BigDecimal getSpeedAfter() {
        return speedAfter;
    }

    public void setSpeedAfter(BigDecimal speedAfter) {
        this.speedAfter = speedAfter;
    }

    public BigDecimal getSpeedValue() {
        return speedValue;
    }

    public void setSpeedValue(BigDecimal speedValue) {
        this.speedValue = speedValue;
    }

    public BigDecimal getAlarmDistance() {
        return alarmDistance;
    }

    public void setAlarmDistance(BigDecimal alarmDistance) {
        this.alarmDistance = alarmDistance;
    }

    public Date getMachineTime() {
        return machineTime;
    }

    public void setMachineTime(Date machineTime) {
        this.machineTime = machineTime;
    }

    public Date getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(Date receiverTime) {
        this.receiverTime = receiverTime;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
