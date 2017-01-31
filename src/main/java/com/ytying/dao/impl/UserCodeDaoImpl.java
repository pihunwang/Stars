package com.ytying.dao.impl;

import com.ytying.dao.UserCodeDao;
import com.ytying.entity.UserCode;
import com.ytying.util.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by kefan.wkf on 17/1/30.
 */
@Repository
@Transactional
public class UserCodeDaoImpl implements UserCodeDao {

    @Autowired
    private HibernateUtils hibernateUtils;

    @Override
    public int addUserCode(UserCode userCode) {
        hibernateUtils.add(userCode);
        return userCode.getUid();
    }

}
