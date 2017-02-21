package com.ytying.dao.impl;

import com.ytying.dao.ChapterDao;
import com.ytying.entity.Chapter;
import com.ytying.entity.User;
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
 * Created by kefan.wkf on 17/2/20.
 */
@Repository
@Transactional
public class ChapterDapImpl implements ChapterDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Chapter> getAllChapter() {
        List<Chapter> chapterList = new ArrayList<Chapter>();
        String hsql = "from Chapter";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hsql);
        chapterList = query.list();
        return chapterList;
    }
}
