package com.ytying.controller;

import com.ytying.cache.CataLogCache;
import com.ytying.entity.Article;
import com.ytying.packaged.CatalogDo;
import com.ytying.service.ArticleService;
import com.ytying.sysenum.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/catalogListGet")
    @ResponseBody
    public String getCatalogList() {
        List<CatalogDo> list = CataLogCache.getCataLog();
        if (list != null && list.size() > 0) {
            return jsonResultNew(ReturnCode.RETURN_SUCCESS, list);
        }
        return jsonResultNew(ReturnCode.RETURN_ERROR, null);
    }

    @RequestMapping(value = "/articleGet")
    @ResponseBody
    public String getArticleByTitleId(@RequestParam String title_id) {
        Article article = articleService.getArticleByTitleId(title_id);
        if (article != null) {
            return jsonResultNew(ReturnCode.RETURN_SUCCESS, article);
        }
        return jsonResultNew(ReturnCode.RETURN_ERROR, null);
    }

}
