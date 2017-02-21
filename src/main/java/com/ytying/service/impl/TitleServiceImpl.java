package com.ytying.service.impl;

import com.ytying.dao.TitleDao;
import com.ytying.entity.Title;
import com.ytying.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleDao titleDao;

    @Override
    public List<Title> getTitleListFromChapter(String parentIndex) {
        return titleDao.getTitleListFromChapter(parentIndex);
    }
}
