package com.mcsoft.oauth.dao;

import com.mcsoft.common.model.OauthToken;

public interface OauthTokenMapper {
    int deleteByPrimaryKey(Integer tokenId);

    int insert(OauthToken record);

    int insertSelective(OauthToken record);

    OauthToken selectByPrimaryKey(Integer tokenId);

    int updateByPrimaryKeySelective(OauthToken record);

    int updateByPrimaryKey(OauthToken record);
}