package com.ytying.service.impl;

import com.ytying.dao.ChapterDao;
import com.ytying.entity.Chapter;
import com.ytying.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public List<Chapter> getAllChapter() {
        return chapterDao.getAllChapter();
    }
}
