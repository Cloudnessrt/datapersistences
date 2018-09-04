package com.singularity.datapersistence.test;


import com.singularity.datapersistence.DatapersistenceApplication;
import com.singularity.datapersistence.common.InitSqlTool;
import com.singularity.datapersistence.service.inside.impl.MysqlEntityDeal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatapersistenceApplication.class)
public class Tests {

    @Autowired
    private Testservice testservice;
    @Autowired
    private InitSqlTool initSqlTool;

    @Autowired
    private MysqlEntityDeal mysqlEntityDeal;
    @Test
    public void testShow() throws Exception {
        initSqlTool.init();
        String expectedResult="hello world!";
        testservice.query();
        testservice.save();

    }
}
