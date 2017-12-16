package com.ytying.dao.impl;

import com.ytying.dao.ArticleDao;
import com.ytying.entity.Article;
import com.ytying.entity.User;
import com.ytying.util.RedisUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Repository
@Transactional
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Jedis jedis;

    @Override
    public Article getArticleByTitleId(String titleId) {
        Article article = null;
        try {
            article = RedisUtils.getObject(titleId, Article.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (article != null) {
            return article;
        } else {
            Query query = sessionFactory.getCurrentSession().createQuery("from Article a where a.title_id = :title_id").setParameter("title_id", titleId);
            List<Article> list = query.list();
            if (list != null && list.size() > 0) {
                RedisUtils.setObject(titleId, list.get(0), 0);
                return list.get(0);
            }
            return null;
        }
    }
}
