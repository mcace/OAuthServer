package com.mcsoft.oauth.service.impl;

import com.mcsoft.account.dao.UserAccountMapper;
import com.mcsoft.common.model.UserAccount;
import com.mcsoft.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author : Mc
 * @date : 2017/4/7 13:47
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public boolean isUserSignIn(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        return !(null == username) && !(null == userAccountMapper.getUserAccountByUsername(username));
    }

    @Override
    public UserAccount getUserAccount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (null == username) return null;
        return userAccountMapper.getUserAccountByUsername(username);
    }
}
