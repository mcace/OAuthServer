package com.mcsoft.common.model;

import java.util.Date;

public class OauthToken {
    private Integer tokenId;

    private String userOpenId;

    private String accessToken;

    private String refreshToken;

    private Date createDate;

    private Date accessTokenExpireDate;

    private Date refreshTokenExpireDate;

    private String scope;

    private String state;

    private Integer refreshCount;

    private String tokenStatues;

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId == null ? null : userOpenId.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getAccessTokenExpireDate() {
        return accessTokenExpireDate;
    }

    public void setAccessTokenExpireDate(Date accessTokenExpireDate) {
        this.accessTokenExpireDate = accessTokenExpireDate;
    }

    public Date getRefreshTokenExpireDate() {
        return refreshTokenExpireDate;
    }

    public void setRefreshTokenExpireDate(Date refreshTokenExpireDate) {
        this.refreshTokenExpireDate = refreshTokenExpireDate;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(Integer refreshCount) {
        this.refreshCount = refreshCount;
    }

    public String getTokenStatues() {
        return tokenStatues;
    }

    public void setTokenStatues(String tokenStatues) {
        this.tokenStatues = tokenStatues == null ? null : tokenStatues.trim();
    }
}