package com.ytying;

import com.ytying.entity.Article;
import com.ytying.entity.UserCode;
import com.ytying.service.UserCodeService;
import com.ytying.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by kefan.wkf on 17/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestUserCodeDao {

    @Autowired
    private UserCodeService userCodeService;

    @Test
    public void testUserCodeList() {
        List<UserCode> list = userCodeService.getUserCodeList(5);
        for (UserCode userCode : list) {
            System.out.println(userCode.getUid());
            System.out.println(userCode.getSource_code());
        }
    }

    private Jedis jedis;

    @Test
    public void testRedis() {

        Article article = new Article();
        article.setId(1);
        article.setContent("hah");
        article.setTitle("皮");

        RedisUtils.setObject("article", article, 0);
        Article article1 = RedisUtils.getObject("article",Article.class);
        if(article1 != null) {
            System.out.println(article1.getTitle());
        }
    }
}
