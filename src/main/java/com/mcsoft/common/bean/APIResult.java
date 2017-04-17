package com.mcsoft.common.bean;

/**
 * 接口调用返回参数
 *
 * @author : Mc
 * @date : 2017/4/7 15:17
 */
public class APIResult {
    private String code;//状态码
    private Object data;//返回数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
