package com.mcsoft.oauth.service;

import com.mcsoft.common.model.UserAccount;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户状态服务，验证用户登录等功能
 * 目前只实现了用本地session来验证用户登录，未来希望可以加入SSO使用
 *
 * @author : Mc
 * @date : 2017/4/7 11:21
 */
public interface UserService {

    /**
     * 通过Session获取用户登录状态
     *
     * @param request HTTP请求
     * @return 用户是否登录
     * @throws Exception
     */
    boolean isUserSignIn(HttpServletRequest request) throws Exception;

    /**
     * 获取用户信息
     * @param request HTTP请求
     * @return 用户信息
     */
    UserAccount getUserAccount(HttpServletRequest request);
}
