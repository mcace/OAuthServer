package com.mcsoft.common.model;

import java.util.Date;

public class AppInfo {
    private Integer appId;

    private String appSecret;

    private String appName;

    private String appHost;

    private String appOwner;

    private String verifyHostCode;

    private Date accessDate;

    private Date expireDate;

    private Date refreshDate;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppHost() {
        return appHost;
    }

    public void setAppHost(String appHost) {
        this.appHost = appHost == null ? null : appHost.trim();
    }

    public String getAppOwner() {
        return appOwner;
    }

    public void setAppOwner(String appOwner) {
        this.appOwner = appOwner == null ? null : appOwner.trim();
    }

    public String getVerifyHostCode() {
        return verifyHostCode;
    }

    public void setVerifyHostCode(String verifyHostCode) {
        this.verifyHostCode = verifyHostCode == null ? null : verifyHostCode.trim();
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getRefreshDate() {
        return refreshDate;
    }

    public void setRefreshDate(Date refreshDate) {
        this.refreshDate = refreshDate;
    }
}