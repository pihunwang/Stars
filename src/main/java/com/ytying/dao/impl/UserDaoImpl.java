package com.ytying.dao.impl;

import com.ytying.dao.UserDao;
import com.ytying.entity.User;
import com.ytying.packaged.UserAccountDo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by UKfire on 17/1/16.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserAccountDo doLogin(String user_name, String password) {

        return null;
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<User>();
        String hsql = "from User";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        userList = query.list();
        return userList;
    }

    public boolean isExists(String user_name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.user_name = :user_name").setParameter("user_name", user_name);
        System.out.println(query.list().size());
        return query.list().size() > 0 ? true : false;
    }
}
