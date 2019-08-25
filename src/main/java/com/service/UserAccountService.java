package com.service;

import com.dao.UserAccountDao;
import com.pojo.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountDao dao;

    public UserAccount getUser(Integer userId) {
        return dao.selectByPrimaryKey(new UserAccount(userId));
    }

    @Transactional
    public void insert(Integer userId, String userName, Integer money) {
        UserAccount userAccount = new UserAccount(userId);
        userAccount.setMoney(money);
        userAccount.setUserName(userName);
        dao.insert(userAccount);
    }
}
