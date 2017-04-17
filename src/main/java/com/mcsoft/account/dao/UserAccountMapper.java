package com.mcsoft.account.dao;

import com.mcsoft.common.model.UserAccount;

import java.util.Map;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    UserAccount getUserAccountByUsernameAndPwd(Map paramMap);

    /**
     * 通过用户名查询用户，该查询忽略大小写
     * (等于查询重复用户时，不区分大小写，但登录时区分大小写，这样就不会出现相同字母用户名因大小写不同被视为不同账号的情况，同时登录时还要保证账号精确)
     *
     * @param username 用户名
     * @return 用户账号
     */
    UserAccount getUserAccountByUsername(String username);

    int insertUser(UserAccount userAccount);
}