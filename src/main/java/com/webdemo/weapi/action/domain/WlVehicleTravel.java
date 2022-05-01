package com.webdemo.weapi.action.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 车辆实时行程表
 * @author dpf
 * @date 19/8/1
 * @version v1.01
 */
public class WlVehicleTravel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long travelId;

    private String travelCode;

    private String travelName;

    private Date logTime;

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

    private String discharge;

    private Integer fuelType;

    private Date startTime;

    private Date endTime;

    private BigDecimal startLongitude;

    private BigDecimal startLatitude;

    private BigDecimal endLongitude;

    private BigDecimal endLatitude;

    private String startAddress;

    private String endAddress;

    private String startAddressZh;

    private String endAddressZh;

    private Integer travleEndType;

    private Integer isEnd;

    private BigDecimal mileage;

    private BigDecimal fuel;

    private BigDecimal lazySpeedFuel;

    private BigDecimal drivingAvgFuel;

    private BigDecimal avgFuel;

    private BigDecimal driverTime;

    private BigDecimal avgSpeed;

    private Integer overspeedTimes;

    private BigDecimal overspeedDistance;

    private BigDecimal overspeedDuration;

    private Integer accelerateTimes;

    private Integer decelerateTimes;

    private Integer sharpTurnTimes;

    private Integer fatigueTimes;

    private BigDecimal fatigueDuration;

    private Integer lazySpeedTimes;

    private BigDecimal lazySpeedDuration;

    private Integer neutralTimes;

    private BigDecimal excessRatio;

    private BigDecimal startFuel;

    private BigDecimal endFuel;

    private BigDecimal startMileage;

    private BigDecimal endMileage;

    private Integer mileageTypes;

    private Integer fuelTypes;

    private Integer isObd;

    private Integer isSummary;

    private Integer isProblem;

    private Integer isError;

    private BigDecimal score;

    private Integer isShow;

    private BigDecimal mileageOriginal;

    private BigDecimal fuelOriginal;

    private Date calculationTime;

    private Integer calculationTimes;

    private Long startTimeLong;

    private Long refStartTime;

    private Long refEndTime;

    private String newTravelCode;

    private Integer turnOverTimes;

    private Integer collisionTimes;

    private Integer onlineState;

    private Integer acc;

    private BigDecimal maxSpeed;//最大速度

    private BigDecimal maxRpm;//最高转速



    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getMaxRpm() {
        return maxRpm;
    }

    public void setMaxRpm(BigDecimal maxRpm) {
        this.maxRpm = maxRpm;
    }

    public Integer getAcc() {
        return acc;
    }

    public void setAcc(Integer acc) {
        this.acc = acc;
    }

    public Integer getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    public Integer getTurnOverTimes() {
        return turnOverTimes;
    }

    public void setTurnOverTimes(Integer turnOverTimes) {
        this.turnOverTimes = turnOverTimes;
    }

    public Integer getCollisionTimes() {
        return collisionTimes;
    }

    public void setCollisionTimes(Integer collisionTimes) {
        this.collisionTimes = collisionTimes;
    }

    public String getNewTravelCode() {
        return newTravelCode;
    }

    public void setNewTravelCode(String newTravelCode) {
        this.newTravelCode = newTravelCode;
    }

    public Long getRefStartTime() {
        return refStartTime;
    }

    public void setRefStartTime(Long refStartTime) {
        this.refStartTime = refStartTime;
    }

    public Long getRefEndTime() {
        return refEndTime;
    }

    public void setRefEndTime(Long refEndTime) {
        this.refEndTime = refEndTime;
    }

    public Long getStartTimeLong() {
        return startTimeLong;
    }

    public void setStartTimeLong(Long startTimeLong) {
        this.startTimeLong = startTimeLong;
    }

    public Integer getCalculationTimes() {
        return calculationTimes;
    }

    public void setCalculationTimes(Integer calculationTimes) {
        this.calculationTimes = calculationTimes;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
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

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
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

    public String getDischarge() {
        return discharge;
    }

    public void setDischarge(String discharge) {
        this.discharge = discharge == null ? null : discharge.trim();
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
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

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress == null ? null : startAddress.trim();
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress == null ? null : endAddress.trim();
    }

    public String getStartAddressZh() {
        return startAddressZh;
    }

    public void setStartAddressZh(String startAddressZh) {
        this.startAddressZh = startAddressZh == null ? null : startAddressZh.trim();
    }

    public String getEndAddressZh() {
        return endAddressZh;
    }

    public void setEndAddressZh(String endAddressZh) {
        this.endAddressZh = endAddressZh == null ? null : endAddressZh.trim();
    }

    public Integer getTravleEndType() {
        return travleEndType;
    }

    public void setTravleEndType(Integer travleEndType) {
        this.travleEndType = travleEndType;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getFuel() {
        return fuel;
    }

    public void setFuel(BigDecimal fuel) {
        this.fuel = fuel;
    }

    public BigDecimal getLazySpeedFuel() {
        return lazySpeedFuel;
    }

    public void setLazySpeedFuel(BigDecimal lazySpeedFuel) {
        this.lazySpeedFuel = lazySpeedFuel;
    }

    public BigDecimal getDrivingAvgFuel() {
        return drivingAvgFuel;
    }

    public void setDrivingAvgFuel(BigDecimal drivingAvgFuel) {
        this.drivingAvgFuel = drivingAvgFuel;
    }

    public BigDecimal getAvgFuel() {
        return avgFuel;
    }

    public void setAvgFuel(BigDecimal avgFuel) {
        this.avgFuel = avgFuel;
    }

    public BigDecimal getDriverTime() {
        return driverTime;
    }

    public void setDriverTime(BigDecimal driverTime) {
        this.driverTime = driverTime;
    }

    public BigDecimal getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(BigDecimal avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Integer getOverspeedTimes() {
        return overspeedTimes;
    }

    public void setOverspeedTimes(Integer overspeedTimes) {
        this.overspeedTimes = overspeedTimes;
    }

    public BigDecimal getOverspeedDistance() {
        return overspeedDistance;
    }

    public void setOverspeedDistance(BigDecimal overspeedDistance) {
        this.overspeedDistance = overspeedDistance;
    }

    public BigDecimal getOverspeedDuration() {
        return overspeedDuration;
    }

    public void setOverspeedDuration(BigDecimal overspeedDuration) {
        this.overspeedDuration = overspeedDuration;
    }

    public Integer getAccelerateTimes() {
        return accelerateTimes;
    }

    public void setAccelerateTimes(Integer accelerateTimes) {
        this.accelerateTimes = accelerateTimes;
    }

    public Integer getDecelerateTimes() {
        return decelerateTimes;
    }

    public void setDecelerateTimes(Integer decelerateTimes) {
        this.decelerateTimes = decelerateTimes;
    }

    public Integer getSharpTurnTimes() {
        return sharpTurnTimes;
    }

    public void setSharpTurnTimes(Integer sharpTurnTimes) {
        this.sharpTurnTimes = sharpTurnTimes;
    }

    public Integer getFatigueTimes() {
        return fatigueTimes;
    }

    public void setFatigueTimes(Integer fatigueTimes) {
        this.fatigueTimes = fatigueTimes;
    }

    public BigDecimal getFatigueDuration() {
        return fatigueDuration;
    }

    public void setFatigueDuration(BigDecimal fatigueDuration) {
        this.fatigueDuration = fatigueDuration;
    }

    public Integer getLazySpeedTimes() {
        return lazySpeedTimes;
    }

    public void setLazySpeedTimes(Integer lazySpeedTimes) {
        this.lazySpeedTimes = lazySpeedTimes;
    }

    public BigDecimal getLazySpeedDuration() {
        return lazySpeedDuration;
    }

    public void setLazySpeedDuration(BigDecimal lazySpeedDuration) {
        this.lazySpeedDuration = lazySpeedDuration;
    }

    public Integer getNeutralTimes() {
        return neutralTimes;
    }

    public void setNeutralTimes(Integer neutralTimes) {
        this.neutralTimes = neutralTimes;
    }

    public BigDecimal getExcessRatio() {
        return excessRatio;
    }

    public void setExcessRatio(BigDecimal excessRatio) {
        this.excessRatio = excessRatio;
    }

    public BigDecimal getStartFuel() {
        return startFuel;
    }

    public void setStartFuel(BigDecimal startFuel) {
        this.startFuel = startFuel;
    }

    public BigDecimal getEndFuel() {
        return endFuel;
    }

    public void setEndFuel(BigDecimal endFuel) {
        this.endFuel = endFuel;
    }

    public BigDecimal getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(BigDecimal startMileage) {
        this.startMileage = startMileage;
    }

    public BigDecimal getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(BigDecimal endMileage) {
        this.endMileage = endMileage;
    }

    public Integer getMileageTypes() {
        return mileageTypes;
    }

    public void setMileageTypes(Integer mileageTypes) {
        this.mileageTypes = mileageTypes;
    }

    public Integer getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(Integer fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public Integer getIsObd() {
        return isObd;
    }

    public void setIsObd(Integer isObd) {
        this.isObd = isObd;
    }

    public Integer getIsSummary() {
        return isSummary;
    }

    public void setIsSummary(Integer isSummary) {
        this.isSummary = isSummary;
    }

    public Integer getIsProblem() {
        return isProblem;
    }

    public void setIsProblem(Integer isProblem) {
        this.isProblem = isProblem;
    }

    public Integer getIsError() {
        return isError;
    }

    public void setIsError(Integer isError) {
        this.isError = isError;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public BigDecimal getMileageOriginal() {
        return mileageOriginal;
    }

    public void setMileageOriginal(BigDecimal mileageOriginal) {
        this.mileageOriginal = mileageOriginal;
    }

    public BigDecimal getFuelOriginal() {
        return fuelOriginal;
    }

    public void setFuelOriginal(BigDecimal fuelOriginal) {
        this.fuelOriginal = fuelOriginal;
    }

    public Date getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(Date calculationTime) {
        this.calculationTime = calculationTime;
    }


}
