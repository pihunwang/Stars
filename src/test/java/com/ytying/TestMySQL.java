package com.ytying;

import com.ytying.cache.TokenCache;
import com.ytying.controller.UserController;
import com.ytying.dao.UserAccountDao;
import com.ytying.entity.User;
import com.ytying.entity.UserAccount;
import com.ytying.service.UserAccountService;
import com.ytying.service.UserService;
import com.ytying.service.impl.UserServiceImpl;
import com.ytying.util.HibernateUtils;
import com.ytying.util.TokenUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by kefan.wkf on 17/1/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMySQL {

    @Test
    public void testHello() {
        System.out.println("Hello Junit");
    }

//    @Autowired
//    public ApplicationContext context;

    @Autowired
    private HibernateUtils hibernateUtils;

    @Autowired
    private UserService userService;

    @Test
    public void testDataSource() throws SQLException {
        User user = userService.doLogin("admin","caca357");
        System.out.println(user.getUser_name());
    }

}