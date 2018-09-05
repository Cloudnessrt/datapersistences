package com.singularity.datapersistence.bean;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class SqlBasicInfo implements Cloneable {

    private String schema;//表空间
    private String tableName;//表名
    private String primaryKey;//主键名
    private List<ColInfo> col=new ArrayList<>();//列
    private String insertSql;
    private String updateSql;

    public SqlBasicInfo() { }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ColInfo> getCol() {
        return col;
    }

    public void setCol(List<ColInfo> col) {
        this.col = col;
    }

    public String getInsertSql() {
        return insertSql;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("{" +
                "Schema:"+getSchema()+"," +
                "TableName:"+getTableName()+"," +
                "PrimaryKey:"+getPrimaryKey()+"," +
                 "Col:"+JSON.toJSONString(col)+"," +
                "}");
        return stringBuilder.toString();
    }

    @Override
    public SqlBasicInfo clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return (SqlBasicInfo)super.clone();
    }
}
