package com.mcsoft.app.service;

import com.mcsoft.common.model.AppApplication;
import com.mcsoft.common.model.AppInfo;

/**
 * 第三方应用相关服务
 *
 * @author : Mc
 * @date : 2017/4/7 16:37
 */
public interface AppService {

    /**
     * 通过应用id及应用密钥获取应用信息
     *
     * @param appId     应用id
     * @param appSecret 应用密钥
     * @return 应用信息，如果没有获取到返回null
     */
    AppInfo getAppInfo(int appId, String appSecret);

    /**
     * 通过应用id获取应用信息
     *
     * @param appId 应用id
     * @return 应用信息
     */
    AppInfo getAppInfoByAppId(int appId);

    /**
     * 通过应用id获取应用信息
     *
     * @param appId 应用id
     * @return 应用信息
     */
    AppInfo getAppInfoInServiceByAppId(int appId);

    /**
     * 第三方申请接入
     *
     * @param appName 应用名
     * @param host    应用网站
     * @param owner   应用所有者
     * @return 应用申请
     */
    AppApplication applyAppAccess(String appName, String host, String owner);

    /**
     * 获取应用申请
     *
     * @param appName 应用名
     * @return 应用申请
     */
    AppApplication getApplicationByAppName(String appName);
}
