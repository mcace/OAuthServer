package com.mcsoft.oauth.service.impl;

import com.mcsoft.common.bean.OAuthConstants;
import com.mcsoft.common.utils.StringUtils;
import com.mcsoft.oauth.bean.AuthRequest;
import com.mcsoft.oauth.service.AuthService;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * @author : Mc
 * @date : 2017/4/13 9:32
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    public boolean isAuthRedirectRequest(HttpServletRequest request) {
        String method = request.getMethod();
        return StringUtils.isEmpty(method) || method.equals(OAuth.HttpMethod.GET)
                || !"1".equals(request.getParameter(OAuthConstants.Params.UPDATE_AUTH));
    }

    @Override
    public boolean isConfirmingAuthRequest(HttpServletRequest request) {
        String method = request.getMethod();
        return !StringUtils.isEmpty(method) && method.equals(OAuth.HttpMethod.POST)
                && "1".equals(request.getParameter(OAuthConstants.Params.UPDATE_AUTH));
    }

    public boolean isLegalRedirectURI(String host, String redirectURI) {
        try {
            redirectURI = URLDecoder.decode(redirectURI, "utf-8");//先处理url编码

            //正则验证url，形如http://www.xxx.com/xxx或http://www.xxx.com/xxx?xxx=ss
            Pattern uriPattern = Pattern
                    .compile("^[a-zA-Z]+:\\/\\/(\\.?(\\w+([-|~|_]\\w+)*))*(:\\d+)?([\\/|\\.](\\w+([-|~|_]\\w+)*))*(\\s*)?(\\?.*)*$");

            return !StringUtils.hasEmpty(host, redirectURI)
                    && uriPattern.matcher(redirectURI).matches()
                    && redirectURI.startsWith(host);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    @Override
    public AuthRequest persistAuthData(HttpServletRequest request) throws Exception {
        AuthRequest requestData = new AuthRequest(request);
        request.getSession().setAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA, requestData);
        return requestData;
    }

    @Override
    public AuthRequest lookupAuthData(HttpServletRequest request) {
        return (AuthRequest) request.getSession().getAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA);
    }

    @Override
    public boolean isRequestDataNotModified(HttpServletRequest request) throws Exception {
        AuthRequest persistData = (AuthRequest) request.getSession().getAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA);
        if (null == persistData) throw OAuthUtils.handleOAuthProblemException(OAuthConstants.Messages.REQUEST_ILLEGAL);
        return persistData.equals(new AuthRequest(request));
    }

    @Override
    public AuthRequest cleanPersistAuthData(HttpServletRequest request) {
        AuthRequest persistData = (AuthRequest) request.getSession().getAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA);
        //清除session中的数据
        request.getSession().removeAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA);
        return persistData;
    }
}
