package com.ytying.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kefan.wkf on 17/1/14.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uid;
    private String user_name;
    private String password;

    public User(){

    }

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
