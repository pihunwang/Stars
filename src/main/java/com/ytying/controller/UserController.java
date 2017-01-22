package com.ytying.controller;

import com.ytying.entity.User;
import com.ytying.service.UserService;
import com.ytying.sysenum.ReturnCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kefan.wkf on 17/1/18.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private transient final Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/UserLogin")
    @ResponseBody
    public String userLogin(@RequestParam String user_name,
                            @RequestParam String password){
        //TODO
        return "";
    }

    @RequestMapping(value = "/UserListGet")
    @ResponseBody
    public String getAllUser() {
        List<User> list = userService.getAllUser();
        log.info(list.get(3).getUser_name());
        return jsonResultNew(ReturnCode.RETURN_SUCCESS, list);
    }

}
