package com.ytying.dao;

import com.ytying.entity.Title;

import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
public interface TitleDao {

    List<Title> getTitleListFromChapter(String parentIndex);
}
