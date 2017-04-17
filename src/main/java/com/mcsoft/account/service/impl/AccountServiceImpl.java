package com.mcsoft.account.service.impl;

import com.mcsoft.account.dao.UserAccountMapper;
import com.mcsoft.account.service.AccountService;
import com.mcsoft.common.model.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : Mc
 * @date : 2017/4/7 13:47
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public UserAccount signIn(final String username, final String md5pwd) throws Exception {

        return userAccountMapper.getUserAccountByUsernameAndPwd(new HashMap<String, Object>() {
            {
                put("username", username);
                put("md5pwd", md5pwd);
            }
        });
    }

    @Override
    public UserAccount signUp(String username, String md5pwd, String nickname) throws Exception {
        UserAccount account = new UserAccount();
        account.setUsername(username);
        account.setPassword(md5pwd);
        account.setNickname(nickname);

        UserAccount accountRegistered = userAccountMapper.getUserAccountByUsername(username);
        if (null != accountRegistered) {
            return null;
        }

        int id = userAccountMapper.insertUser(account);
        if (0 == id) {
            return null;
        }
        account.setId(id);
        return account;
    }

    @Override
    public UserAccount getUserByUsername(String username) throws Exception {
        return userAccountMapper.getUserAccountByUsername(username);
    }

    @Override
    public UserAccount getUserByUserId(int userId) throws Exception {
        return userAccountMapper.selectByPrimaryKey(userId);
    }

}
