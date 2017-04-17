package com.mcsoft.app.service.impl;

import com.mcsoft.app.dao.AppApplicationMapper;
import com.mcsoft.app.dao.AppInfoMapper;
import com.mcsoft.app.service.AppService;
import com.mcsoft.common.model.AppApplication;
import com.mcsoft.common.model.AppInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Mc
 * @date : 2017/4/7 16:39
 */
@Service
@Transactional
public class AppServiceImpl implements AppService {
    @Resource
    private AppInfoMapper appInfoMapper;
    @Resource
    private AppApplicationMapper appApplicationMapper;

    @Override
    public AppInfo getAppInfo(int appId, String appSecret) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("appSecret", appSecret);
        return appInfoMapper.getByAppIdAndAppSecret(paramMap);
    }

    @Override
    public AppInfo getAppInfoByAppId(int appId) {
        return appInfoMapper.selectByPrimaryKey(appId);
    }

    @Override
    public AppInfo getAppInfoInServiceByAppId(int appId) {
        return appInfoMapper.getAppInServiceByAppId(appId);
    }

    @Override
    public AppApplication applyAppAccess(String appName, String host, String owner) {
        AppApplication newApplication = new AppApplication();
        newApplication.setAppName(appName);
        newApplication.setAppHost(host);
        newApplication.setAppOwner(owner);
        newApplication.setApplyDate(new Date());
        int id = appApplicationMapper.insertSelective(newApplication);
        if (0 == id) {
            return null;
        }
        newApplication.setApplyId(id);
        return newApplication;
    }

    @Override
    public AppApplication getApplicationByAppName(String appName) {
        return appApplicationMapper.getApplicationByAppName(appName);
    }
}
