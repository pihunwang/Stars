package com.ytying.service;

import com.ytying.entity.UserAccount;

import java.util.List;

/**
 * Created by kefan.wkf on 17/1/20.
 */
public interface UserAccountService {

    //获取用户登录凭证
    UserAccount getUserAccountByUid(int uid);

    //重新生成用户登录凭证
    UserAccount getNewUserAccountByUid(int uid);

    //保存用户登录凭证
    int addUserAccount(UserAccount userAccount);

    //更新用户登录凭证
    int updateUserAccount(UserAccount newAccount);

    //删除用户登录凭证
    int deleteUserAccount(int uid);

    //得到所有用户凭证
    List<UserAccount> getUserAccountList();
}
