package com.mcsoft.account.service;

import com.mcsoft.common.model.UserAppRelation;

/**
 * 用户OpenID相关服务
 *
 * @author : Mc
 * @date : 2017/4/13 10:39
 */
public interface OpenIDService {

    /**
     * 通过用户id、应用id获取对应的用户OpenID，如果不存在则创建
     *
     * @param userID 用户id
     * @param appID  应用id
     * @return OpenID对象
     */
    public UserAppRelation getOrCreateUserOpenID(int userID, int appID);
}
