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

    public class Params{
        public static final String AUTH_REQUEST_DATA = "auth_request_data";
        public static final int AUTHORIZATION_CODE_EXPIRE_MINUTES = 10;
    }
}
