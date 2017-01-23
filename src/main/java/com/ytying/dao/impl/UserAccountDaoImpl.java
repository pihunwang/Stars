package com.ytying.dao.impl;

import com.ytying.dao.UserAccountDao;
import com.ytying.entity.User;
import com.ytying.entity.UserAccount;
import com.ytying.util.HibernateUtils;
import com.ytying.util.TokenUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kefan.wkf on 17/1/20.
 */
@Repository
@Transactional
public class UserAccountDaoImpl implements UserAccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateUtils hibernateUtils;

    public UserAccount getUserAccountByUid(int uid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserAccount ua where ua.uid = :uid").setParameter("uid", uid);
        List<UserAccount> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public UserAccount getNewUserAccountByUid(int uid) {
        String token = TokenUtils.getTokenByUid(uid);
        UserAccount account = new UserAccount();
        account.setToken(token);
        account.setUid(uid);
        account.setC_time(new Date().getTime());
        return account;
    }

    public int addUserAccount(UserAccount userAccount) {
        hibernateUtils.add(userAccount);
        return userAccount.getUid();
    }

    public int updateUserAccount(UserAccount newAccount) {
        hibernateUtils.update(newAccount);
        return newAccount.getUid();
    }

    public int deleteUserAccount(int uid) {
        User user = new User();
        user.setUid(uid);
        hibernateUtils.delete(user);
        return uid;
    }

    public List<UserAccount> getUserAccountList() {
        List<UserAccount> accList = new ArrayList<UserAccount>();
        String hsql = "from UserAccount";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        accList = query.list();
        return accList;
    }
}
