package com.ytying.service.impl;

import com.ytying.dao.UserAccountDao;
import com.ytying.entity.UserAccount;
import com.ytying.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kefan.wkf on 17/1/20.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    public UserAccount getUserAccountByUid(int uid) {
        return userAccountDao.getUserAccountByUid(uid);
    }

    public UserAccount getNewUserAccountByUid(int uid) {
        return userAccountDao.getNewUserAccountByUid(uid);
    }

    public int addUserAccount(UserAccount userAccount) {
        return userAccountDao.addUserAccount(userAccount);
    }

    public int updateUserAccount(UserAccount newAccount) {
        return userAccountDao.updateUserAccount(newAccount);
    }

    public int deleteUserAccount(int uid) {
        return userAccountDao.deleteUserAccount(uid);
    }

    public List<UserAccount> getUserAccountList() {
        return userAccountDao.getUserAccountList();
    }
}
