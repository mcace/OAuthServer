package com.mcsoft.oauth.bean;

import com.mcsoft.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 授权请求数据对象
 *
 * @author : Mc
 * @date : 2017/4/14 9:47
 */
public class AuthRequest {
    private String response_type;
    private String client_id;
    private String redirect_uri;
    private String scope;
    private String state;

    public AuthRequest() {
    }

    public AuthRequest(HttpServletRequest request) throws Exception {
        this.setClient_id(request.getParameter("client_id"));
        this.setRedirect_uri(URLEncoder.encode(request.getParameter("redirect_uri"), "utf-8"));
        this.setResponse_type(request.getParameter("response_type"));
        this.setScope(request.getParameter("scope"));
        this.setState(request.getParameter("state"));
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //判断两个授权请求参数是否一致
    public boolean equals(AuthRequest req) throws Exception {
        return myCompare(this.response_type, req.response_type)
                && myCompare(this.client_id, req.client_id)
                && myCompare(this.redirect_uri, req.redirect_uri)//比较时必须是
                //&& myCompare(this.scope, req.scope)//用户可选授权范围，不做修改验证了
                && myCompare(this.state, req.state);
    }

    private boolean myCompare(String a, String b) throws UnsupportedEncodingException {
        return a == b || !StringUtils.hasEmpty(a, b) || a.equals(b) || URLDecoder.decode(a, "utf-8").equals(b)
                || URLDecoder.decode(b, "utf-8").equals(a);
    }

    @Override
    public String toString() {
        return (StringUtils.isEmpty(this.response_type) ? "" : "response_type=" + response_type + "&")
                + (StringUtils.isEmpty(this.client_id) ? "" : "client_id=" + client_id + "&")
                + (StringUtils.isEmpty(this.scope) ? "" : "scope=" + scope + "&")
                + (StringUtils.isEmpty(this.state) ? "" : "state=" + state + "&")
                + (StringUtils.isEmpty(this.redirect_uri) ? "" : "redirect_uri=" + redirect_uri);
    }

    public String concatAuthURI(String uri) {
        if (uri.contains("?")) {
            return uri + "&" + this.toString();
        } else {
            return uri + "?" + this.toString();
        }
    }

    public static String concatAuthURI(HttpServletRequest request, String uri) throws Exception {
        return new AuthRequest(request).concatAuthURI(uri);
    }
}
