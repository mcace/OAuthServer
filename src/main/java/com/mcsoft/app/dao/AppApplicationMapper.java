package com.mcsoft.app.dao;

import com.mcsoft.common.model.AppApplication;

public interface AppApplicationMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(AppApplication record);

    int insertSelective(AppApplication record);

    AppApplication selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(AppApplication record);

    int updateByPrimaryKey(AppApplication record);

    AppApplication getApplicationByAppName(String appName);
}