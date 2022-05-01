package com.webdemo.weapi.action.domain.entity;

import java.io.Serializable;

public class WlAlarmType  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String alarmId;
    private Integer alarmClass;//告警分类,1:普通告警,2:驾驶行为,3.严重告警,4.故障,5.电子围栏,6.安防,7.ACC,8电动车,9设备上报
    private Integer alarmType;//告警类型,1:AM告警,2:RT事件
    private String alarmName;
    private String alarmNameEn;


    public String getAlarmNameEn() {
        return alarmNameEn;
    }

    public void setAlarmNameEn(String alarmNameEn) {
        this.alarmNameEn = alarmNameEn;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId == null ? null : alarmId.trim();
    }

    public Integer getAlarmClass() {
        return alarmClass;
    }

    public void setAlarmClass(Integer alarmClass) {
        this.alarmClass = alarmClass;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }
}
