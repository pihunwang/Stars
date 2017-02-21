package com.ytying.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Entity
@Table(name = "title")
public class Title {

    @Id
    private String title_id;
    private String article_title;
    private String parent_index;

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getParent_index() {
        return parent_index;
    }

    public void setParent_index(String parent_index) {
        this.parent_index = parent_index;
    }
}
