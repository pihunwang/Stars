package com.ytying.cache;

import com.ytying.entity.UserAccount;
import com.ytying.service.UserAccountService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kefan.wkf on 17/1/20.
 */
@Component
public class TokenCache implements InitializingBean {

    @Autowired
    private UserAccountService userAccountService;

    private static Map<String,UserAccount> tokenCache = new HashMap<String, UserAccount>();

    public static void put(String token,UserAccount account){
        tokenCache.put(token,account);
    }

    public static UserAccount get(String token){
        return tokenCache.get(token);
    }

    public static void remove(UserAccount account){
        tokenCache.remove(account.getToken());
    }

    public static int tokenSize(){
        return tokenCache.size();
    }

    public void afterPropertiesSet() throws Exception {
        for(UserAccount acc : userAccountService.getUserAccountList()){
            tokenCache.put(acc.getToken(),acc);
        }
        System.out.println("**********User Acc Token Init Over!*************");
    }
}
