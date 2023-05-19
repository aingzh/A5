package com.yx.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 数据封闭类，返回给前端
 */
@ApiModel(description= "返回响应数据，类json")
public class DataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类状态码，0成功，400失败")
    private Integer code;
    @ApiModelProperty(value = "状态码描述信息，没啥有效信息")
    private String msg;
    @ApiModelProperty(value = "主要的返回数据，通常是pojo的list")
    private Object data; //json数据
    @ApiModelProperty(value = "分页数量，不太了解")
    private Long count; // 分页信息：总条数

    public DataInfo() {
    }

    public DataInfo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataInfo(Integer code, String msg, Object data, Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public static DataInfo ok() {
        return new DataInfo(Constants.OK_CODE, Constants.OK_MSG, null);
    }

    public static DataInfo ok(Object data) {
        return new DataInfo(Constants.OK_CODE, Constants.OK_MSG, data);
    }

    public static DataInfo ok(String msg, long count, Object data) {
        return new DataInfo(Constants.OK_CODE, Constants.OK_MSG, data, count);
    }

    public static DataInfo ok(String msg, Object data) {
        return new DataInfo(Constants.OK_CODE, msg, data);
    }

    public static DataInfo fail(String msg) {
        return new DataInfo(Constants.FAIL_CODE, msg, null);
    }
    public static DataInfo fail(String msg,Object data) {
        return new DataInfo(Constants.FAIL_CODE, msg, data);
    }
    public static DataInfo fail(int errorCode, String msg) {
        return new DataInfo(errorCode, msg, null);
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
