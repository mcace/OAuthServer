package com.mcsoft.account.service;

import com.mcsoft.common.model.UserAccount;

/**
 * 账号登录，账号注册等功能实现
 *
 * @author : Mc
 * @date : 2017/4/7 11:09
 */
public interface AccountService {

    /**
     * 验证用户名密码
     *
     * @param username 用户名
     * @param md5pwd   md5处理后密码
     * @return 找到用户则返回用户帐户实体，未找到则返回null
     */
    public UserAccount signIn(String username, String md5pwd) throws Exception;

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param md5pwd   md5处理后密码
     * @param nickname 用户昵称
     * @return 用户帐户
     */
    public UserAccount signUp(String username, String md5pwd, String nickname) throws Exception;

    /**
     * 通过用户名获取用户(不区分大小写)
     *
     * @param username 用户名
     * @return 用户帐户信息
     */
    public UserAccount getUserByUsername(String username) throws Exception;

    /**
     * 通过用户ID获取用户
     *
     * @param userId 用户ID
     * @return 用户帐户信息
     * @throws Exception
     */
    public UserAccount getUserByUserId(int userId) throws Exception;
}
