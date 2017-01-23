package com.ytying.dao;

import com.ytying.entity.User;
import com.ytying.packaged.UserAccountDo;

import java.util.List;

/**
 * Created by kefan.wkf on 17/1/16.
 */
public interface UserDao {

    User doLogin(String user_name, String password);

    List<User> getAllUser();

    boolean isExists(String user_name);

    int addUser(User user);
}
