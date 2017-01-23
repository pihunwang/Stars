package com.ytying.controller;

import com.ytying.cache.TokenCache;
import com.ytying.entity.User;
import com.ytying.entity.UserAccount;
import com.ytying.packaged.UserAccountDo;
import com.ytying.service.UserAccountService;
import com.ytying.service.UserService;
import com.ytying.sysenum.ReturnCode;
import com.ytying.sysenum.TokenType;
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

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/UserLogin")
    @ResponseBody
    public String userLogin(@RequestParam String user_name,
                            @RequestParam String password) {
        User user = userService.doLogin(user_name, password);

        if (null == user) {
            return jsonResultNew(ReturnCode.RETURN_USERNAMEORPASSERROR, null);
        } else {
            UserAccount newAccount = userAccountService.getNewUserAccountByUid(user.getUid());
            newAccount.setSurvive_time(TokenType.SHORTTIME.survivetime);

            UserAccount oldAccount = userAccountService.getUserAccountByUid(user.getUid());

            int rst = null == oldAccount ? userAccountService.addUserAccount(newAccount) : userAccountService.updateUserAccount(newAccount);

            if (rst < 1) {
                return jsonResultNew(ReturnCode.RETURN_UserLogin_FAIL_SERVICERROR, null);
            }
            if (oldAccount != null) {
                //移除旧token
                TokenCache.remove(oldAccount);
            }
            //缓存新token
            TokenCache.put(newAccount.getToken(), newAccount);

            UserAccountDo userAccountDo = new UserAccountDo();
            userAccountDo.setUid(user.getUid());
            userAccountDo.setUser_name(user.getUser_name());
            userAccountDo.setToken(newAccount.getToken());
            return jsonResultNew(ReturnCode.RETURN_UserLogin_SUCCESS, userAccountDo);
        }
    }

    @ResponseBody
    @RequestMapping("/UserAdd")
    public String userAdd(@RequestParam String user_name,
                          @RequestParam String password) {
        User user = new User();
        user.setUser_name(user_name);
        user.setPassword(password);

        int rst = userService.addUser(user);
        if (rst == 0) {
            return jsonResultNew(ReturnCode.RETURN_USER_ALREADY_EXISTS, null);
        } else if (rst == -1) {
            return jsonResultNew(ReturnCode.RETURN_ERROR, null);
        } else if(rst > 0){
            return jsonResultNew(ReturnCode.RETURN_SUCCESS,user);
        }
        return jsonResultNew(ReturnCode.RETURN_ERROR, null);
    }

    @RequestMapping(value = "/UserListGet")
    @ResponseBody
    public String getAllUser() {
        List<User> list = userService.getAllUser();
        return jsonResultNew(ReturnCode.RETURN_SUCCESS, list);
    }

}
