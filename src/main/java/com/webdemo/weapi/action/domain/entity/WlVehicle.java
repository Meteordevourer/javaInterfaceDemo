package com.webdemo.weapi.action.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WlVehicle implements Serializable {

	private Long vehicleId;

	private String vehicleCode;

	private String vehicleNick;

	private Integer enterpriseId;

	private String operatorCode;

	private String deviceCode;

	private String userCode;

	private String vehicleNumber;

	private String vehicleNumber2;

	private String plateColor;

	private String vehicleImg;

	private Integer brandId;

	private Integer seriesId;

	private Integer modelId;

	private String modelName;

	private Integer spareTyres;

	private String color;

	private Date factoryDt;

	private Integer usageType;

	private Integer vehicleClass;

	private String vin;

	private BigDecimal discharge;

	private String emissionrulecode;

	private Date buyDate;

	private Integer fuelType;

	private Date registerDate;

	private String manufacturer;

	private Integer vehicleState;

	private Integer maxPerson;

	private Integer maxLoad;

	private Integer tyres;

	private String icp;

	private Integer netWeight;

	private Integer wheelbase;

	private String engineModel;

	private String engineNo;

	private BigDecimal maxFuel;

	private BigDecimal ratedPower;

	private BigDecimal mileage;

	private BigDecimal vehiclePrice;

	private String drivingOwner;

	private Integer vehicleFrom;

	private String vehicleLicensePath;

	private Date nextMaintanceTime;

	private BigDecimal nextMaintanceMileage;

	private Date nextInsuranceTime;

	private Date nextExaminTime;

	private Integer isObd;

	private BigDecimal fullPower;

	private BigDecimal oilDensity;

	private String noticeInfo;

	private String remark;

	private Date createdTm;

	private String modifiedEmpCode;

	private Date modifiedTm;

	private Integer year;

	private Integer fortificationFlag;

	private Integer score;

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode == null ? null : vehicleCode.trim();
	}

	public String getVehicleNick() {
		return vehicleNick;
	}

	public void setVehicleNick(String vehicleNick) {
		this.vehicleNick = vehicleNick == null ? null : vehicleNick.trim();
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

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode == null ? null : deviceCode.trim();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? null : userCode.trim();
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber == null ? null : vehicleNumber.trim();
	}

	public String getVehicleNumber2() {
		return vehicleNumber2;
	}

	public void setVehicleNumber2(String vehicleNumber2) {
		this.vehicleNumber2 = vehicleNumber2 == null ? null : vehicleNumber2.trim();
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor == null ? null : plateColor.trim();
	}

	public String getVehicleImg() {
		return vehicleImg;
	}

	public void setVehicleImg(String vehicleImg) {
		this.vehicleImg = vehicleImg == null ? null : vehicleImg.trim();
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
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

	public Integer getSpareTyres() {
		return spareTyres;
	}

	public void setSpareTyres(Integer spareTyres) {
		this.spareTyres = spareTyres;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color == null ? null : color.trim();
	}

	public Date getFactoryDt() {
		return factoryDt;
	}

	public void setFactoryDt(Date factoryDt) {
		this.factoryDt = factoryDt;
	}

	public Integer getUsageType() {
		return usageType;
	}

	public void setUsageType(Integer usageType) {
		this.usageType = usageType;
	}

	public Integer getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(Integer vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin == null ? null : vin.trim();
	}

	public BigDecimal getDischarge() {
		return discharge;
	}

	public void setDischarge(BigDecimal discharge) {
		this.discharge = discharge;
	}

	public String getEmissionrulecode() {
		return emissionrulecode;
	}

	public void setEmissionrulecode(String emissionrulecode) {
		this.emissionrulecode = emissionrulecode == null ? null : emissionrulecode.trim();
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getFuelType() {
		return fuelType;
	}

	public void setFuelType(Integer fuelType) {
		this.fuelType = fuelType;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer == null ? null : manufacturer.trim();
	}

	public Integer getVehicleState() {
		return vehicleState;
	}

	public void setVehicleState(Integer vehicleState) {
		this.vehicleState = vehicleState;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public Integer getMaxLoad() {
		return maxLoad;
	}

	public void setMaxLoad(Integer maxLoad) {
		this.maxLoad = maxLoad;
	}

	public Integer getTyres() {
		return tyres;
	}

	public void setTyres(Integer tyres) {
		this.tyres = tyres;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp == null ? null : icp.trim();
	}

	public Integer getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Integer netWeight) {
		this.netWeight = netWeight;
	}

	public Integer getWheelbase() {
		return wheelbase;
	}

	public void setWheelbase(Integer wheelbase) {
		this.wheelbase = wheelbase;
	}

	public String getEngineModel() {
		return engineModel;
	}

	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel == null ? null : engineModel.trim();
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo == null ? null : engineNo.trim();
	}

	public BigDecimal getMaxFuel() {
		return maxFuel;
	}

	public void setMaxFuel(BigDecimal maxFuel) {
		this.maxFuel = maxFuel;
	}

	public BigDecimal getRatedPower() {
		return ratedPower;
	}

	public void setRatedPower(BigDecimal ratedPower) {
		this.ratedPower = ratedPower;
	}

	public BigDecimal getMileage() {
		return mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	public BigDecimal getVehiclePrice() {
		return vehiclePrice;
	}

	public void setVehiclePrice(BigDecimal vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}

	public String getDrivingOwner() {
		return drivingOwner;
	}

	public void setDrivingOwner(String drivingOwner) {
		this.drivingOwner = drivingOwner == null ? null : drivingOwner.trim();
	}

	public Integer getVehicleFrom() {
		return vehicleFrom;
	}

	public void setVehicleFrom(Integer vehicleFrom) {
		this.vehicleFrom = vehicleFrom;
	}

	public String getVehicleLicensePath() {
		return vehicleLicensePath;
	}

	public void setVehicleLicensePath(String vehicleLicensePath) {
		this.vehicleLicensePath = vehicleLicensePath == null ? null : vehicleLicensePath.trim();
	}

	public Date getNextMaintanceTime() {
		return nextMaintanceTime;
	}

	public void setNextMaintanceTime(Date nextMaintanceTime) {
		this.nextMaintanceTime = nextMaintanceTime;
	}

	public BigDecimal getNextMaintanceMileage() {
		return nextMaintanceMileage;
	}

	public void setNextMaintanceMileage(BigDecimal nextMaintanceMileage) {
		this.nextMaintanceMileage = nextMaintanceMileage;
	}

	public Date getNextInsuranceTime() {
		return nextInsuranceTime;
	}

	public void setNextInsuranceTime(Date nextInsuranceTime) {
		this.nextInsuranceTime = nextInsuranceTime;
	}

	public Date getNextExaminTime() {
		return nextExaminTime;
	}

	public void setNextExaminTime(Date nextExaminTime) {
		this.nextExaminTime = nextExaminTime;
	}

	public Integer getIsObd() {
		return isObd;
	}

	public void setIsObd(Integer isObd) {
		this.isObd = isObd;
	}

	public BigDecimal getFullPower() {
		return fullPower;
	}

	public void setFullPower(BigDecimal fullPower) {
		this.fullPower = fullPower;
	}

	public BigDecimal getOilDensity() {
		return oilDensity;
	}

	public void setOilDensity(BigDecimal oilDensity) {
		this.oilDensity = oilDensity;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo == null ? null : noticeInfo.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getCreatedTm() {
		return createdTm;
	}

	public void setCreatedTm(Date createdTm) {
		this.createdTm = createdTm;
	}

	public String getModifiedEmpCode() {
		return modifiedEmpCode;
	}

	public void setModifiedEmpCode(String modifiedEmpCode) {
		this.modifiedEmpCode = modifiedEmpCode == null ? null : modifiedEmpCode.trim();
	}

	public Date getModifiedTm() {
		return modifiedTm;
	}

	public void setModifiedTm(Date modifiedTm) {
		this.modifiedTm = modifiedTm;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getFortificationFlag() {
		return fortificationFlag;
	}

	public void setFortificationFlag(Integer fortificationFlag) {
		this.fortificationFlag = fortificationFlag;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}