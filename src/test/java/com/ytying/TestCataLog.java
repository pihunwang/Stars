package com.ytying;

import com.ytying.cache.CataLogCache;
import com.ytying.packaged.CatalogDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by kefan.wkf on 17/2/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestCataLog {

    @Test
    public void testCataLog() {
        List<CatalogDo> list = CataLogCache.getCataLog();
        System.out.println(list.size());
    }
}
