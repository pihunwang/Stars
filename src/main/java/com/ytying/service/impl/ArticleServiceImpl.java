package com.ytying.service.impl;

import com.ytying.dao.ArticleDao;
import com.ytying.entity.Article;
import com.ytying.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article getArticleByTitleId(String titleId) {
        return articleDao.getArticleByTitleId(titleId);
    }
}
