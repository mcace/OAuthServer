package com.mcsoft.account.controller;

import com.mcsoft.account.bean.UserInfo;
import com.mcsoft.account.service.AccountService;
import com.mcsoft.common.bean.APIResult;
import com.mcsoft.common.bean.OAuthConstants;
import com.mcsoft.common.model.UserAccount;
import com.mcsoft.common.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 账号相关Controller，登录可以用SSO进行，这个Controller模拟了登录和注册行为，仅供演示使用
 *
 * @author : Mc
 * @date : 2017/4/6 15:42
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    private Logger logger = Logger.getLogger(AccountController.class);

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginRedirect() {
        return "/account/login.html";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String signUpRedirect() {
        return "/account/signup";
    }

    /**
     * 登录
     *
     * @param session  服务器session
     * @param username 用户名
     * @param pwd      处理后密码
     * @return 调用返回信息
     */
    @ResponseBody
    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public APIResult signIn(HttpSession session, String username, String pwd) {
        APIResult result = new APIResult();
        result.setCode(OAuthConstants.Code.OK);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)) {
            //参数错误
            result.setCode(OAuthConstants.Code.INVALID_REQUEST);
            return result;
        }
        try {
            UserAccount account = accountService.signIn(username, pwd);
            if (null == account) {
                //未查询到用户，账号或密码错误
                result.setCode(OAuthConstants.Code.INVALID_NAME_OR_PWD);
                return result;
            }
            //查询到用户
            session.setAttribute("username", username);
            session.setAttribute("userId", account.getId());

            account.setPassword(null);//不再返回用户密码
            result.setData(account);
            return result;
        } catch (Exception e) {
            //查询时出错
            logger.error("用户登录进行账号密码验证时服务器出错,用户名:" + username + ",密码:" + pwd);
            logger.error(e);

            result.setCode(OAuthConstants.Code.BAD_REQUEST);
            return result;
        }


    }

    /**
     * 注册
     *
     * @param username 用户名
     * @param pwd      密码
     * @param nickname 昵称
     * @return 调用返回信息
     */
    @ResponseBody
    @RequestMapping(value = "sing-up", method = RequestMethod.POST)
    public APIResult signUp(String username, String pwd, String nickname) {
        APIResult result = new APIResult();
        result.setCode(OAuthConstants.Code.OK);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(nickname)) {
            //参数错误
            result.setCode(OAuthConstants.Code.INVALID_REQUEST);
            return result;
        }

        try {
            UserAccount account = accountService.getUserByUsername(username);
            if (null != account) {
                //账号重复
                result.setCode(OAuthConstants.Code.USERNAME_DUPLICATED);
                return result;
            }
            account = accountService.signUp(username, pwd, nickname);
            if (null == account) {
                logger.error("用户注册保存数据库失败，用户名:" + username + ",密码:" + pwd + ",昵称:" + nickname);
                //保存失败
                result.setCode(OAuthConstants.Code.BAD_REQUEST);
                return result;
            }
            account.setPassword(null);//不再返回用户密码
            result.setData(account);
            return result;
        } catch (Exception e) {
            logger.error("注册时发生错误，用户名:" + username + ",密码:" + pwd + ",昵称:" + nickname);
            logger.error(e);
            result.setCode(OAuthConstants.Code.BAD_REQUEST);
            return result;
        }
    }

    /**
     * 获取用户信息
     *
     * @param session 服务器session
     * @return 调用结果
     */
    public APIResult getUserAccountInfo(HttpSession session) {
        APIResult result = new APIResult();
        result.setCode(OAuthConstants.Code.OK);

        String username = (String) session.getAttribute("username");
        if (null == username) {
            result.setCode(OAuthConstants.Code.USER_NOLOGIN);
            return result;
        }

        try {
            UserAccount account = accountService.getUserByUsername(username);
            UserInfo userInfo = UserInfo.transformUserAccount(account);
            result.setData(userInfo);
            return result;
        } catch (Exception e) {
            logger.error("查询用户信息时服务器发生错误");
            logger.error(e);
            result.setCode(OAuthConstants.Code.BAD_REQUEST);
            return result;
        }
    }
}
