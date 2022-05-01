package com.webdemo.weapi.action.domain.entity;



import com.webdemo.weapi.utils.Constant;

import java.io.Serializable;

/**
 * 返回数据
 */
public class ResponseObject implements Serializable {

    private static final long serialVersionUID = 1L;
	private Integer code;
	private Object data;

	private String resCode;
	private String resMsg;
	private String resSrt;
	private String version;
	
	
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getResSrt() {
		return resSrt;
	}

	public void setResSrt(String resSrt) {
		this.resSrt = resSrt;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public ResponseObject() {
        this.code = Constant.RESPONSE_CODE_SUCCESS;
        this.data = "";
	}
	
	public static ResponseObject error(Object data) {
		ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(Constant.RESPONSE_ERROR_CODE);
        responseObject.setData(data);
		return responseObject;
	}

    public static ResponseObject error(Integer code, Object data) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(code);
        responseObject.setData(data);
        return responseObject;
    }

    public static ResponseObject errorParam() {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(Constant.RESPONSE_PARAM_ERROR_CODE);
        responseObject.setData(Constant.RESPONSE_PARAM_INVALID);
        return responseObject;
    }

	public static ResponseObject success(Object data) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(data);
        return responseObject;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
