package com.mcsoft.oauth.service.impl;

import com.mcsoft.common.bean.OAuthConstants;
import com.mcsoft.common.utils.StringUtils;
import com.mcsoft.oauth.bean.AuthRequest;
import com.mcsoft.oauth.service.AuthService;
import org.apache.oltu.oauth2.common.OAuth;
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
                || !"1".equals(request.getParameter("update_auth"));
    }

    @Override
    public boolean isConfirmedAuthRequest(HttpServletRequest request) {
        String method = request.getMethod();
        return !StringUtils.isEmpty(method) || method.equals(OAuth.HttpMethod.POST)
                || "1".equals(request.getParameter("update_auth"));
    }

    public boolean isLegalRedirectURI(String host, String redirectURI) {
        try {
            //正则验证url，形如http://www.xxx.com/xxx或http://www.xxx.com/xxx?xxx=ss
            Pattern uriPattern = Pattern
                    .compile("^[a-zA-Z]+:\\/\\/(\\.?(\\w+([-|~|_]\\w+)*))*(:\\d+)?([\\/|\\.](\\w+([-|~|_]\\w+)*))*(\\s*)?(\\?.*)*$");

            return StringUtils.hasEmpty(host, redirectURI)
                    || uriPattern.matcher(redirectURI).matches()
                    || URLDecoder.decode(redirectURI, "utf-8").startsWith(host);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    @Override
    public void persistAuthData(HttpServletRequest request) {
        AuthRequest requestData = new AuthRequest(request);
        request.getSession().setAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA, requestData);
    }

    @Override
    public AuthRequest lookupAuthData(HttpServletRequest request) {
        return (AuthRequest) request.getSession().getAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA);
    }

    @Override
    public boolean isRequestDataNotModified(HttpServletRequest request) {
        AuthRequest persistData = (AuthRequest) request.getSession().getAttribute(OAuthConstants.Params.AUTH_REQUEST_DATA);
        return persistData.equals(new AuthRequest(request));
    }
}
