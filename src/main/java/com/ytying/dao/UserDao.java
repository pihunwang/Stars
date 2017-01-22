package com.ytying.dao;

import com.ytying.entity.User;
import com.ytying.packaged.UserAccountDo;

import java.util.List;

/**
 * Created by kefan.wkf on 17/1/16.
 */
public interface UserDao {

    UserAccountDo doLogin(String user_name, String password);

    //得到所有用户
    List<User> getAllUser();

    boolean isExists(String user_name);
}
