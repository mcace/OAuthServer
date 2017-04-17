package com.mcsoft.oauth.service;

import com.mcsoft.common.model.AuthorizationCode;
import com.mcsoft.common.model.OauthToken;
import org.apache.oltu.oauth2.common.token.OAuthToken;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权服务，生成authorization code、换取accessToken等功能
 *
 * @author : Mc
 * @date : 2017/4/7 11:09
 */
public interface TokenService {

    /**
     * 生成authorization code
     *
     * @param request Http请求
     * @param openID  用户与应用间关联ID
     * @return 授权code
     * @throws Exception
     */
    AuthorizationCode createAuthorizationCode(HttpServletRequest request, String openID) throws Exception;

    OAuthToken createAccessToken(AuthorizationCode code) throws Exception;

    OauthToken refreshAccessToken() throws Exception;
}
