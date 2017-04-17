package com.mcsoft.oauth.controller;

import com.mcsoft.account.service.OpenIDService;
import com.mcsoft.app.service.AppService;
import com.mcsoft.common.bean.OAuthConstants;
import com.mcsoft.common.model.AppInfo;
import com.mcsoft.common.model.AuthorizationCode;
import com.mcsoft.common.model.UserAccount;
import com.mcsoft.common.model.UserAppRelation;
import com.mcsoft.common.utils.StringUtils;
import com.mcsoft.oauth.bean.AuthRequest;
import com.mcsoft.oauth.service.AuthService;
import com.mcsoft.oauth.service.TokenService;
import com.mcsoft.oauth.service.UserService;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OAuth Controller
 *
 * @author : Mc
 * @date : 2017/4/6 14:37
 */
@Controller
@RequestMapping("/oauth2.0")
public class AuthzController {
    public static final Logger logger = Logger.getLogger(AuthzController.class);

    @Resource
    private UserService userService;
    @Resource
    private OpenIDService openIDService;
    @Resource
    private AppService appService;
    @Resource
    private AuthService authService;
    @Resource
    private TokenService tokenService;


    @RequestMapping("/authorize")
    public void authroize(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes)
            throws OAuthSystemException, IOException {
        try {
            //构建OAuth请求，并验证参数格式正确，只接受GET或POST方法
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            //获取应用信息
            AppInfo appInfo = appService.getAppInfoInServiceByAppId(Integer.valueOf(oauthRequest.getClientId()));

            //验证是第一次授权请求(无授权字段或用户未登录)，则记录请求数据并跳转到授权界面
            if (!userService.isUserSignIn(request) || authService.isAuthRedirectRequest(request)) {
                //验证app_id正确
                if (null == appInfo) {
                    throw OAuthUtils.handleOAuthProblemException(OAuthConstants.Messages.CLIENT_ID_ILLEGAL);
                }
                //验证redirectURI正确
                String redirectURI = oauthRequest.getRedirectURI();
                if (!authService.isLegalRedirectURI(appInfo.getAppHost(), redirectURI)) {
                    throw OAuthUtils.handleOAuthProblemException(OAuthConstants.Messages.REDIRECT_URI_ILLEGAL);
                }
                //记录授权请求数据
                //记录redirect_uri等参数，当用户授权后进行一致性验证
                authService.persistAuthData(request);
                //跳转到授权界面，授权界面验证用户登陆情况显示[登录并授权]/[授权]
                response.sendRedirect(AuthRequest.concatURI(request, request.getContextPath() + "/oauth2.0/show"));
                return;
            }

            //验证是第二次授权请求(用户已登录且有授权字段)，即用户主动点击“授权”按钮后的授权请求
            if (authService.isConfirmedAuthRequest(request)) {
                if (!authService.isRequestDataNotModified(request)) {
                    throw OAuthUtils.handleOAuthProblemException(OAuthConstants.Messages.REQUEST_DATA_MODIFIED);
                }
                //获取用户帐号信息
                UserAccount account = userService.getUserAccount(request);

                //获取openID，如果不存在则生成并保存openID到数据库
                UserAppRelation userAppRelation = openIDService.getOrCreateUserOpenID(account.getId(), appInfo.getAppId());
                String openID = userAppRelation.getUserOpenId();

                //生成authorization code，并保存到数据库
                AuthorizationCode code = tokenService.createAuthorizationCode(request, openID);

                //跳转到第三方连接
                OAuthASResponse.OAuthAuthorizationResponseBuilder responseBuilder = OAuthASResponse
                        .authorizationResponse(request, HttpServletResponse.SC_FOUND)
                        .setCode(code.getAuthorzCode())
                        .location(oauthRequest.getRedirectURI());
                //设定state
                String state = oauthRequest.getState();
                if (!StringUtils.isEmpty(state)) {
                    responseBuilder.setParam("state", state);
                }
                OAuthResponse oauthResponse = responseBuilder.buildQueryMessage();
                response.sendRedirect(oauthResponse.getLocationUri());
                return;
            }
            throw OAuthUtils.handleOAuthProblemException("Bad request");
        } catch (Exception ex) {
            logger.error("验证授权时发生错误");
            logger.error(ex);

            OAuthResponse oauthResponse;
            if (ex.getClass().equals(OAuthProblemException.class)) {
                oauthResponse = OAuthResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .error((OAuthProblemException) ex)
                        .buildJSONMessage();
            } else {
                oauthResponse = OAuthResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .error(OAuthProblemException.error("bad_request").description(ex.getMessage()))
                        .buildJSONMessage();
            }
            response.getWriter().write(oauthResponse.getBody());
        }
    }

    @RequestMapping("/show")
    public void show(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes)
            throws OAuthSystemException, IOException {

    }


}