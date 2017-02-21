package com.ytying.cache;

import com.ytying.entity.Chapter;
import com.ytying.entity.Title;
import com.ytying.packaged.CatalogDo;
import com.ytying.service.ChapterService;
import com.ytying.service.TitleService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 * 文章目录缓存器
 */
@Component
public class CataLogCache implements InitializingBean {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private TitleService titleService;

    private static List<CatalogDo> catalogDoList = new ArrayList<>();

    public static List<CatalogDo> getCataLog() {
        return catalogDoList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Chapter> chapterList = chapterService.getAllChapter();
        for (Chapter chapter : chapterList) {
            CatalogDo catalogDo = new CatalogDo();
            catalogDo.setChapter_name(chapter.getChapter_name());
            catalogDo.setIndex(chapter.getIndex());
            List<Title> titleList = titleService.getTitleListFromChapter(chapter.getIndex());
            catalogDo.setTitles(titleList);
            catalogDoList.add(catalogDo);
        }
    }
}
