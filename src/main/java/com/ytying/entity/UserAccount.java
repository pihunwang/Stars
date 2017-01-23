package com.ytying.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kefan.wkf on 17/1/19.
 */
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    private int uid;
    private long c_time;
    private long survive_time;
    private String token;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getC_time() {
        return c_time;
    }

    public void setC_time(long c_time) {
        this.c_time = c_time;
    }

    public long getSurvive_time() {
        return survive_time;
    }

    public void setSurvive_time(long survive_time) {
        this.survive_time = survive_time;
    }
}
