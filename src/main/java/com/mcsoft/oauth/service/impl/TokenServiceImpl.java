package com.mcsoft.oauth.service.impl;

import com.mcsoft.common.bean.OAuthConstants;
import com.mcsoft.common.model.AuthorizationCode;
import com.mcsoft.common.model.OauthToken;
import com.mcsoft.common.utils.StringUtils;
import com.mcsoft.oauth.bean.AuthRequest;
import com.mcsoft.oauth.dao.AuthorizationCodeMapper;
import com.mcsoft.oauth.service.TokenService;
import org.apache.oltu.oauth2.common.token.OAuthToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * @author : Mc
 * @date : 2017/4/7 13:47
 */
@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    @Resource
    private AuthorizationCodeMapper authorizationCodeMapper;

    @Override
    public AuthorizationCode createAuthorizationCode(HttpServletRequest request, String openID) throws Exception {
        if (StringUtils.isEmpty(openID)) {
            return null;
        }
        AuthRequest authRequest = (AuthRequest) request.getSession().getAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA);

        AuthorizationCode record = new AuthorizationCode();
        record.setUserOpenId(openID);
        record.setScope(authRequest.getScope());
        //生成授权code
        String code = StringUtils.generateUUIDWithoutCross(openID, String.valueOf(System.currentTimeMillis()));
        record.setAuthorzCode(code.toUpperCase());

        //创建时间
        Calendar calendar = Calendar.getInstance();
        record.setCreateDate(calendar.getTime());
        //过期时间
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + OAuthConstants.Params.AUTHORIZATION_CODE_EXPIRE_MINUTES);
        Date expireDate = calendar.getTime();
        record.setExpireDate(expireDate);

        int id = authorizationCodeMapper.insertSelective(record);
        if (0 == id) {
            return null;
        }
        record.setCodeId(id);
        return record;
    }

    @Override
    public OAuthToken createAccessToken(AuthorizationCode code) throws Exception {
        return null;
    }

    @Override
    public OauthToken refreshAccessToken() throws Exception {
        return null;
    }
}
