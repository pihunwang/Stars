package com.ytying.service;

import com.ytying.entity.Title;

import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
public interface TitleService {
    List<Title> getTitleListFromChapter(String parentIndex);

}
