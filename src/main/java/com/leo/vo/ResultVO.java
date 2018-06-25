package com.leo.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("结果集")
public class ResultVO<T> {

	@ApiModelProperty("代码")
    private String code;

	@ApiModelProperty("信息")
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)	//为null时不发送
    private List<T> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Error> errors;
    
    public ResultVO() {
    }

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public ResultVO<T> addData(T data) {
        if(this.data == null) {
            this.data = new ArrayList<T>();
        }
        this.data.add(data);
        return this;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
    
}
