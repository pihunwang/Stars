package com.ytying.packaged;

import com.ytying.entity.User;

/**
 * Created by kefan.wkf on 17/1/19.
 */
public class UserAccountDo {

    private int uid;
    private String user_name;
    private String token;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
