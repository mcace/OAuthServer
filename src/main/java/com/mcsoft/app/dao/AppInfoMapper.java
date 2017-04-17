package com.mcsoft.app.dao;

import com.mcsoft.common.model.AppInfo;

import java.util.Map;

public interface AppInfoMapper {
    int deleteByPrimaryKey(Integer appInfoId);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    AppInfo getByAppIdAndAppSecret(Map paramMap);

    AppInfo getAppInServiceByAppId(Integer appId);
}