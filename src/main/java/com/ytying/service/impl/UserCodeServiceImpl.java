package com.ytying.service.impl;

import com.ytying.dao.UserCodeDao;
import com.ytying.dao.UserDao;
import com.ytying.entity.UserCode;
import com.ytying.service.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kefan.wkf on 17/1/30.
 */
@Service
public class UserCodeServiceImpl implements UserCodeService {

    @Autowired
    private UserCodeDao userCodeDao;

    @Override
    public int addUserCode(UserCode userCode) {
        return userCodeDao.addUserCode(userCode);
    }

    @Override
    public List<UserCode> getUserCodeList(int uid) {
        return userCodeDao.getUserCodeList(uid);
    }

    @Override
    public boolean removeUserCode(int id, int uid) {
        return userCodeDao.removeUserCode(id,uid);
    }

}
