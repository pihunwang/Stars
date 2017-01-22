package com.ytying.controller;

import com.ytying.packaged.ResultDo;
import com.ytying.sysenum.ReturnCode;
import com.ytying.util.JsonUtils;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public class BaseController {

    protected String jsonResultNew(ReturnCode info, Object data) {
        return JsonUtils.Object2Json(new ResultDo(info, data));
    }

}


