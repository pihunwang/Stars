package com.ytying.service;

import com.ytying.entity.User;
import com.ytying.packaged.UserAccountDo;

import java.util.List;

/**
 * Created by kefan.wkf on 17/1/16.
 */
public interface UserService {

    User doLogin(String user_name, String password);

    List<User> getAllUser();

    boolean isExists(String userName);

    int addUser(User user);
}
