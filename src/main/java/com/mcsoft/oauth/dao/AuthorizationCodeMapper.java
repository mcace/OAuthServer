package com.mcsoft.oauth.dao;

import com.mcsoft.common.model.AuthorizationCode;

public interface AuthorizationCodeMapper {
    int deleteByPrimaryKey(Integer codeId);

    int insert(AuthorizationCode record);

    int insertSelective(AuthorizationCode record);

    AuthorizationCode selectByPrimaryKey(Integer codeId);

    int updateByPrimaryKeySelective(AuthorizationCode record);

    int updateByPrimaryKey(AuthorizationCode record);
}