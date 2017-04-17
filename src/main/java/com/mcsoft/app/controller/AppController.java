package com.mcsoft.app.controller;

import com.mcsoft.app.service.AppService;
import com.mcsoft.common.bean.APIResult;
import com.mcsoft.common.model.AppApplication;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import com.mcsoft.common.utils.StringUtils;

import javax.annotation.Resource;

/**
 * 第三方客户端接入相关Controller
 * 这块写的不好，应该和用户系统结合的
 *
 * @author : Mc
 * @date : 2017/4/7 16:10
 */
@Controller
public class AppController {
    private Logger logger = Logger.getLogger(AppController.class);

    @Resource
    private AppService appService;

    /**
     * 第三方申请接入API
     *
     * @param appName
     * @param host
     * @param owner
     */
    public APIResult applyAccess(String appName, String host, String owner) {
        APIResult result = new APIResult();
        result.setCode(200);

        if (StringUtils.hasEmpty(appName, host, owner)) {
            result.setCode(401);
            return result;
        }

        try {
            AppApplication application = appService.getApplicationByAppName(appName);
            if (null != application) {
                result.setCode(701);
                return result;
            }
            application = appService.applyAppAccess(appName, host, owner);
            if (null == application) {
                logger.error("第三方提交接入申请保存数据库时失败，appName:" + appName + ",host:" + host + ",owner:" + owner);
                result.setCode(500);
                return result;
            }
            result.setCode(200);
            result.setData(application);
            return result;
        } catch (Exception e) {
            logger.error("第三方提交接入申请时服务器发生错误，appName:" + appName + ",host:" + host + ",owner:" + owner);
            logger.error(e);
            result.setCode(500);
            return result;

        }
    }

    /**
     * 通过应用名查询申请
     *
     * @param appName 应用名
     */
    public APIResult getApplicationByAppName(String appName) {
        APIResult result = new APIResult();
        result.setCode(200);

        if (StringUtils.isEmpty(appName)) {
            result.setCode(401);
            return result;
        }

        try {
            AppApplication application = appService.getApplicationByAppName(appName);
            if (null == application) {
                result.setCode(402);
            }
            result.setData(application);
            return result;
        } catch (Exception e) {
            logger.error("通过appName查询第三方接入申请时发生错误，appName:" + appName);
            logger.error(e);
            result.setCode(500);
            return result;
        }
    }
}
