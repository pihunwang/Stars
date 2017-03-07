package com.ytying.dao.impl;

import com.ytying.dao.UserCodeDao;
import com.ytying.entity.User;
import com.ytying.entity.UserCode;
import com.ytying.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kefan.wkf on 17/1/30.
 */
@Repository
@Transactional
public class UserCodeDaoImpl implements UserCodeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateUtils hibernateUtils;

    @Override
    public int addUserCode(UserCode userCode) {
        hibernateUtils.add(userCode);
        return userCode.getUid();
    }

    @Override
    public List<UserCode> getUserCodeList(int uid) {
        List<UserCode> userCodeList = new ArrayList<UserCode>();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserCode u where u.uid = :uid").setParameter("uid",uid);
        userCodeList = query.list();
        return userCodeList;
    }

    @Override
    public boolean removeUserCode(int id, int uid) {
        UserCode userCode = new UserCode();
        userCode.setId(id);
        userCode.setUid(uid);
        boolean success = hibernateUtils.delete(userCode);
        return success;
    }

}
