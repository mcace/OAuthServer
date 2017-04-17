package com.mcsoft.account.dao;

import com.mcsoft.common.model.UserAppRelation;

import java.util.Map;

public interface UserAppRelationMapper {
    int deleteByPrimaryKey(String userOpenId);

    int insert(UserAppRelation record);

    int insertSelective(UserAppRelation record);

    UserAppRelation selectByPrimaryKey(String userOpenId);

    int updateByPrimaryKeySelective(UserAppRelation record);

    int updateByPrimaryKey(UserAppRelation record);

    UserAppRelation selectByUserIDAndAppID(Map paramMap);

    UserAppRelation selectByOpenID(String openID);
}