package com.mcsoft.account.bean;

import com.mcsoft.common.model.UserAccount;

/**
 * 用户信息类
 *
 * @author : Mc
 * @date : 2017/4/11 16:42
 */
public class UserInfo {
    private String username;
    private String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public static UserInfo transformUserAccount(final UserAccount account) {
        return new UserInfo() {
            {
                setUsername(account.getUsername());
                setNickname(account.getNickname());
            }
        };
    }
}
