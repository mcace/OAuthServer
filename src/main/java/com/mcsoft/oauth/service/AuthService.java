package com.mcsoft.oauth.service;

import com.mcsoft.oauth.bean.AuthRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权相关服务
 *
 * @author : Mc
 * @date : 2017/4/13 9:21
 */
public interface AuthService {

    /**
     * 判断授权请求是第一次进行，从而进行授权页面跳转
     *
     * @param request HTTP请求
     * @return 是否是授权页面跳转请求
     */
    boolean isAuthRedirectRequest(HttpServletRequest request);

    /**
     * 判断授权请求时第二次进行，且已经确认给予授权
     *
     * @param request HTTP请求
     * @return 是否已确认给予授权
     */
    boolean isConfirmingAuthRequest(HttpServletRequest request);

    /**
     * 验证重定向URI是否合法
     * 包括是否设定了redirectURI，该URI是否在应用配置的域名下，URI是否是合法的URI(正则验证)
     *
     * @param host        应用配置的服务器域名
     * @param redirectURI 重定向URI，必须在应用配置的域名下
     * @return URI是否合法
     */
    boolean isLegalRedirectURI(String host, String redirectURI);

    /**
     * 将这次授权请求的数据保存，用于进行授权时比对数据是否改变
     *
     * @param request Http请求对象
     * @return 授权请求数据
     */
    void persistAuthData(HttpServletRequest request);

    /**
     * 获取授权请求的数据
     *
     * @param request Http请求对象
     * @return 授权请求数据
     */
    AuthRequest lookupAuthData(HttpServletRequest request);

    /**
     * 验证授权请求参数没有改变
     *
     * @param request Http请求对象
     * @return 参数是否没有改变
     */
    boolean isRequestDataNotModified(HttpServletRequest request);
}
