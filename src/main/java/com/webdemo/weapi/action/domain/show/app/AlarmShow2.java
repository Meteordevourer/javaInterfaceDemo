package com.webdemo.weapi.action.domain.show.app;


import lombok.Data;

@Data
public class AlarmShow2 extends AlarmShow{
    private Integer onlineState;
    private String plateNumber;
}
