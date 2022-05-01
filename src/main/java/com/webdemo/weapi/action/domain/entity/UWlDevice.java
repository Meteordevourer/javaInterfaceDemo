package com.webdemo.weapi.action.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UWlDevice  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long deviceId;

    private String deviceCode;

    private Integer enterpriseId;

    private String operatorCode;

    private String userCode;

    private String vehicleCode;

    private Date factoryDate;

    private Date installDate;

    private String assetsCoding;

    private String assetsBarcode;

    private String simOperator;

    private String simCode;

    private Date simValiddate;

    private String serialCode;

    private String deviceModel;

    private Integer deviceType;

    private Integer bindstatus;

    private Date macActiveTime;

    private String version;

    private String imsi;

    private Integer validState;

    private String userBelongs;

    private String iccid;

    private BigDecimal fuelCorrect;

    private Integer adaptationStatus;

    private String desKey;

    private Date createDate;

    private Date updateDate;

    private String ssid;

    private String password;

    private Date bindingTime;

    private String softwareVersion;

    private String hardwareVersion;

    private String manufacturer;

    private Integer loginStatus;

    private String modemSoftware;

    private String modemHardware;

    private BigDecimal mileage;

    private BigDecimal sumMileage;

    private Integer gpsState;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
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
        this.operatorCode = operatorCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public String getAssetsCoding() {
        return assetsCoding;
    }

    public void setAssetsCoding(String assetsCoding) {
        this.assetsCoding = assetsCoding;
    }

    public String getAssetsBarcode() {
        return assetsBarcode;
    }

    public void setAssetsBarcode(String assetsBarcode) {
        this.assetsBarcode = assetsBarcode;
    }

    public String getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(String simOperator) {
        this.simOperator = simOperator;
    }

    public String getSimCode() {
        return simCode;
    }

    public void setSimCode(String simCode) {
        this.simCode = simCode;
    }

    public Date getSimValiddate() {
        return simValiddate;
    }

    public void setSimValiddate(Date simValiddate) {
        this.simValiddate = simValiddate;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getBindstatus() {
        return bindstatus;
    }

    public void setBindstatus(Integer bindstatus) {
        this.bindstatus = bindstatus;
    }

    public Date getMacActiveTime() {
        return macActiveTime;
    }

    public void setMacActiveTime(Date macActiveTime) {
        this.macActiveTime = macActiveTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public Integer getValidState() {
        return validState;
    }

    public void setValidState(Integer validState) {
        this.validState = validState;
    }

    public String getUserBelongs() {
        return userBelongs;
    }

    public void setUserBelongs(String userBelongs) {
        this.userBelongs = userBelongs;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public BigDecimal getFuelCorrect() {
        return fuelCorrect;
    }

    public void setFuelCorrect(BigDecimal fuelCorrect) {
        this.fuelCorrect = fuelCorrect;
    }

    public Integer getAdaptationStatus() {
        return adaptationStatus;
    }

    public void setAdaptationStatus(Integer adaptationStatus) {
        this.adaptationStatus = adaptationStatus;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBindingTime() {
        return bindingTime;
    }

    public void setBindingTime(Date bindingTime) {
        this.bindingTime = bindingTime;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getModemSoftware() {
        return modemSoftware;
    }

    public void setModemSoftware(String modemSoftware) {
        this.modemSoftware = modemSoftware;
    }

    public String getModemHardware() {
        return modemHardware;
    }

    public void setModemHardware(String modemHardware) {
        this.modemHardware = modemHardware;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getSumMileage() {
        return sumMileage;
    }

    public void setSumMileage(BigDecimal sumMileage) {
        this.sumMileage = sumMileage;
    }

    public Integer getGpsState() {
        return gpsState;
    }

    public void setGpsState(Integer gpsState) {
        this.gpsState = gpsState;
    }
}
