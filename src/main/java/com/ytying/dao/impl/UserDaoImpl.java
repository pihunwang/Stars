package com.ytying.dao.impl;

import com.ytying.dao.UserDao;
import com.ytying.entity.User;
import com.ytying.packaged.UserAccountDo;
import com.ytying.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    @Autowired
    private HibernateUtils hibernateUtils;

    public User doLogin(String user_name, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.user_name = :user_name and u.password = :password").setParameter("user_name", user_name).setParameter("password", password);
        List<User> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
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
        return query.list().size() > 0 ? true : false;
    }

    public int addUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query checkQuery = session.createQuery("from User u where u.user_name = :user_name").setParameter("user_name", user.getUser_name());
            List<User> checkRepeatList = checkQuery.list();
            if (checkRepeatList.size() > 0) {
                //用户名重复
                return 0;
            } else if (checkRepeatList.size() == 0) {
                session.save(user);
            }
            tx.commit();
            Query getUidQuery = sessionFactory.openSession().createQuery("from User u where u.user_name = :user_name").setParameter("user_name", user.getUser_name());
            List<User> getUidList = getUidQuery.list();
            if (getUidList.size() > 0) {
                return getUidList.get(0).getUid();
            } else {
                //插入失败
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return -1;
    }
}
