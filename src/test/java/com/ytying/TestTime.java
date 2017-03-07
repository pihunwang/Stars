package com.ytying;

import com.ytying.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kefan.wkf on 17/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestTime {

    @Test
    public void testTime(){
        long time = 1488785224530l;
        String format = "yyyy-MM-dd";
        String s = StringUtils.transferLongToDate(format,time);
        System.out.println(s);
    }

}
