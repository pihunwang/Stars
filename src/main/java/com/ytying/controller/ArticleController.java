package com.ytying.controller;

import com.ytying.cache.CataLogCache;
import com.ytying.packaged.CatalogDo;
import com.ytying.sysenum.ReturnCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @RequestMapping(value = "/catalogListGet")
    @ResponseBody
    public String getCatalogList() {
        List<CatalogDo> list = CataLogCache.getCataLog();
        if (list != null && list.size() > 0) {
            return jsonResultNew(ReturnCode.RETURN_SUCCESS, list);
        }
        return jsonResultNew(ReturnCode.RETURN_ERROR, null);
    }

}
