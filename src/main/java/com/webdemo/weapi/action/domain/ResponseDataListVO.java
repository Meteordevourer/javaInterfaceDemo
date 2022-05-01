package com.webdemo.weapi.action.domain;

import java.util.List;
import java.util.Map;

public class ResponseDataListVO extends ResponseBaseVO {
    private static final long serialVersionUID = 1L;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setData(List data){
        this.data = data;
    }

    public void setData(Map<String,Object> data){
        this.data = data;
    }
}
