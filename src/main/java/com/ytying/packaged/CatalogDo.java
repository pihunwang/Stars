package com.ytying.packaged;

import com.ytying.entity.Title;

import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
public class CatalogDo {

    private String chapter_name;

    private String index;

    private List<Title> titles;

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }
}
