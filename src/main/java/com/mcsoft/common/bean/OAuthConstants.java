package com.mcsoft.common.bean;

/**
 * 授权时使用的定值
 *
 * @author : Mc
 * @date : 2017/4/13 15:09
 */
public class OAuthConstants {
    public class Messages {
        public static final String CLIENT_ID_ILLEGAL = "Client id is illegal.";
        public static final String REDIRECT_URI_ILLEGAL = "Redirect URI is illegal.";
        public static final String REQUEST_DATA_MODIFIED = "Parameters are modified.";
    }

    public class Params {
        public static final String AUTH_REQUEST_DATA = "auth_request_data";
        public static final String UPDATE_AUTH = "update_auth";
        public static final int AUTHORIZATION_CODE_EXPIRE_MINUTES = 10;
    }

    public class Code {
        public static final String OK = "200";//请求成功
        //30X:资源相关错误(暂无)

        //40X:请求方发生错误
        public static final String INVALID_REQUEST = "401";//参数错误
        public static final String NO_RESULT = "402";//未查询到数据
        public static final String USER_NOLOGIN = "403";//用户未登录

        public static final String BAD_REQUEST = "500";//500:服务方发生错误

        //60X:账号相关错误
        public static final String USERNAME_DUPLICATED = "601";//601:注册时账号重复
        public static final String INVALID_NAME_OR_PWD = "602";//602:账号或密码错误

        //70X:第三方相关错误
        public static final String APPNAME_DUPLICATED = "701";//701:申请时应用名重复

        //80X:授权相关错误
        public static final String INVALID_REDIRECT_URI = "801";//801:申请授权时redirect_uri错误
    }
}
