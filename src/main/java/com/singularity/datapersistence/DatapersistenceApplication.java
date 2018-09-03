package com.singularity.datapersistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatapersistenceApplication {
    //日志
    private static Logger logger= LoggerFactory.getLogger(DatapersistenceApplication.class);


    public static void main(String[] args) throws Exception {

        SpringApplication.run(DatapersistenceApplication.class, args);
        //InitSqlTool.main();


    }

    public void test(){

    }
}
