package com.mcsoft.oauth.controller;

import com.mcsoft.oauth.service.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Access Token相关Controller
 *
 * @author : Mc
 * @date : 2017/4/12 16:32
 */
@Controller
@RequestMapping("/oauth2.0")
public class TokenController {
    @Resource
    private TokenService tokenService;

    /**
     * 获取token，包括授权码换取及刷新两种方式
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public void token(HttpServletRequest request, HttpServletResponse response) {

    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public void checkToken(HttpServletRequest request) {

    }
}
