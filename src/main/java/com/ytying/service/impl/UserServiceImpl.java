package com.ytying.service.impl;

import com.ytying.dao.UserDao;
import com.ytying.entity.User;
import com.ytying.packaged.UserAccountDo;
import com.ytying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kefan.wkf on 17/1/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User doLogin(String user_name, String password) {
        return userDao.doLogin(user_name,password);
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Cacheable(cacheNames = "isExists", key = "#user_name")
    public boolean isExists(String user_name) {
        return userDao.isExists(user_name);
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }
}
