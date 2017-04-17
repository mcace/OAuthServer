package com.mcsoft.account.service.impl;

import com.mcsoft.account.dao.UserAppRelationMapper;
import com.mcsoft.account.service.OpenIDService;
import com.mcsoft.common.model.UserAppRelation;
import com.mcsoft.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author : Mc
 * @date : 2017/4/13 10:56
 */
@Service
@Transactional
public class OpenIDServiceImpl implements OpenIDService {
    @Resource
    private UserAppRelationMapper userAppRelationMapper;

    @Override
    public UserAppRelation getOrCreateUserOpenID(final int userID, final int appID) {
        if (0 == userID || 0 == appID) {
            return null;
        }

        UserAppRelation relation = userAppRelationMapper.selectByUserIDAndAppID(new HashMap<String, Object>() {
            {
                put("userID", userID);
                put("appID", appID);
            }
        });
        if (null == relation) {
            //生成openID，使用UUID策略
            String openID = StringUtils.generateUUIDWithoutCross(String.valueOf(userID), String.valueOf(appID));
            openID = openID.replace("-", "").toLowerCase();//清除'-'
            relation = new UserAppRelation();
            relation.setUserId(userID);
            relation.setAppId(appID);
            relation.setCreateDate(new Date());
            relation.setUserOpenId(openID);

            userAppRelationMapper.insertSelective(relation);
        }

        return relation;
    }
}
