package com.singularity.datapersistence.config;

import org.springframework.stereotype.Component;

/**
 * 配置类
 */
@Component
public class Config {

    private static class InstanceHolder {
        private static final Config INSTANCE = new Config();
        {
            INSTANCE.configInit("com.singularity.datapersistence.db", "mysql", "mysql",true);
        }
    }

    public static Config getInstance() {

        return InstanceHolder.INSTANCE;
    }

    //扫描路径
    private String path="com.singularity.datapersistence.db";
    //数据库名
    private String schema="users";
    //数据库类型
    private String dataBase="mysql";

    private boolean printSql=false;
    //反馈信息
    private String info="Config配置加载";

    /**
     * 配置初始化
     * @param path 扫描路径
     * @param schema 数据库名
     * @param dataBase 数据库类型
     */
    public void configInit(String path, String schema, String dataBase, boolean printSql) {
        this.path=path;
        this.dataBase=dataBase;
        this.schema=schema;
        this.printSql=true;
        System.out.println(info+"配置内容为"+toString()+"\n");
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getPath() {
        return path;
    }

    public boolean isPrintSql() {
        return printSql;
    }

    public void setPrintSql(boolean printSql) {
        this.printSql = printSql;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("{" +
                "dataBase:"+getDataBase()+"," +
                "schema:"+getSchema()+"," +
                "path:"+getPath()+
                "printSql:"+isPrintSql()+
                "}");
        return stringBuilder.toString();
    }
}
