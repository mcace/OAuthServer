package com.mcsoft.common.model;

import java.util.Date;

public class AuthorizationCode {
    private Integer codeId;

    private String userOpenId;

    private String authorzCode;

    private Date createDate;

    private Date expireDate;

    private String scope;

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId == null ? null : userOpenId.trim();
    }

    public String getAuthorzCode() {
        return authorzCode;
    }

    public void setAuthorzCode(String authorzCode) {
        this.authorzCode = authorzCode == null ? null : authorzCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }
}