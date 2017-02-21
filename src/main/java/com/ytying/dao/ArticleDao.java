package com.ytying.dao;

import com.ytying.entity.Article;

/**
 * Created by kefan.wkf on 17/2/20.
 */
public interface ArticleDao {

    Article getArticleByTitleId(String titleId);
}
