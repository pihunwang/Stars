package com.ytying.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by kefan.wkf on 17/1/19.
 */
@Component
@Transactional
public class HibernateUtils {

    @Autowired
    private SessionFactory sessionFactory;

    public void add(Object entity){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public boolean delete(Object entity){
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public void update(Object entity){
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

}
