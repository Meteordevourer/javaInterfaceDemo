package com.webdemo.weapi.action.domain;

import com.webdemo.weapi.action.domain.entity.AlarmLogEntity;
import lombok.Data;

@Data
public class AlarmLog extends AlarmLogEntity {
    private static final long serialVersionUID = 1L;

    private Integer onlineState;
    private Integer acc;
}
