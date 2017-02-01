package com.ytying;

import com.ytying.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kefan.wkf on 17/1/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestCompile {

    @Test
    public void testStringUtils() {
        System.out.println(StringUtils.replaceClassNameFromSourceCode("public class Main  {\npublic static void main(String[] args){\n}\n}","Gao"));
    }

}
