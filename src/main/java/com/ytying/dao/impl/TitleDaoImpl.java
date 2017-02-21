package com.ytying.dao.impl;

import com.ytying.dao.TitleDao;
import com.ytying.entity.Title;
import com.ytying.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Repository
@Transactional
public class TitleDaoImpl implements TitleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Title> getTitleListFromChapter(String parentIndex) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Title t where t.parent_index = :parent_index").setParameter("parent_index", parentIndex);
        List<Title> titleList = query.list();
        return titleList;
    }
}
