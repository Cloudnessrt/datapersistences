package com.singularity.datapersistence.common;

import com.singularity.datapersistence.bean.ColInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//mysql生成类
@Component
public class SqlCreate {

    //日志
    private static Logger logger= LoggerFactory.getLogger(SqlCreate.class);

    @Autowired
    private SqlBasicCach sqlBasicCach;

    /**
     * 新增语句
     * @param object
     * @param <T>
     * @return
     */
    public  <T> String createInsertSql(T object) throws Exception {
        if(object==null){
            throw new Exception("生成新增语句失败\n");
        }
        StringBuilder sb=new StringBuilder();
        String tableName=object.getClass().getSimpleName().toLowerCase();
        SqlBasicInfo sqlBasicInfo=sqlBasicCach.getSqlCach(tableName);
        if(sqlBasicInfo==null){
            throw new Exception("生成新增语句失败,"+object.getClass().getName()+"没有增加entity注解\n");
        }
        sb.append(" insert into ");
        sb.append(getTableNameInfo(sqlBasicInfo)+"(");
        //用于拼接values中的'?'个数
        StringBuffer sbv = new StringBuffer(" values( ");
        for(ColInfo colInfo : sqlBasicInfo.getCol()){
            /*
             * 拼接字段名，以','隔开
             * 拼接结果 insert into employee( emp_code , emp_name ,
             */
            sb.append(dealDecorate(colInfo.getName())+" , ");

            /*
             * 拼接values中的'?'个数，以','隔开
             * 拼接结果 values(  ? , ? ,
             */

        }
        sbv.append(" {data} ");
        /*
         * 删除拼接好的字符串中最后一个','
         * 得到  insert into employee( emp_code , emp_name
         */
        sb.delete(sb.toString().lastIndexOf(","),sb.length());
        /*
         * 添加第一步拼接结束的')'
         * 得到结果 into employee( emp_code , emp_name  )
         */
        sb.append(" ) ");
        /*
         * 添加第二步拼接结束的')'
         * 得到结果 values(  ? , ?  )
         */
        sbv.append(" ) ");

        /*
         * 将拼接好的values添加进sb中
         * 得到完整的insert语句
         * insert into employee( emp_code , emp_name  )  values(  ? , ?  )
         */
        sb.append(sbv);
        sb.append(";");
        sqlBasicInfo.setInsertSql(sb.toString());
        return sb.toString();
    }

    /**
     * 更新语句
     * @param object
     * @param <T>
     * @return
     */
    public  <T> String createUpdateSql(T object) throws Exception {
        StringBuilder sb=new StringBuilder();
        if(object==null){
            throw new Exception("生成更新语句失败！\n");
        }
        SqlBasicInfo sqlBasicInfo=sqlBasicCach.getSqlCach(object.getClass().getSimpleName().toLowerCase());
        if(object==null){
            throw new Exception("生成更新语句失败,"+object.getClass().getName()+"没有标注entity注解\n");
        }
        if(StringUtils.isEmpty(sqlBasicInfo.getPrimaryKey())){
            throw new Exception("生成更新语句失败,"+object.getClass().getName()+"没有标注id注解\n");
        }
        sb.append(" update ");
        sb.append(getTableNameInfo(sqlBasicInfo));
        sb.append(" set ");
        StringBuffer sqlw = new StringBuffer(" where 1=1 ");
        for(ColInfo colInfo:sqlBasicInfo.getCol()){
            //主键
            if(sqlBasicInfo.getPrimaryKey().equals(colInfo.getName())){
                sqlw.append(" and "+dealDecorate(colInfo.getName())+" = ? ");
            }else{
                sb.append(" "+dealDecorate(colInfo.getName())+"= {"+colInfo.getName()+"} ,");
            }
        }
        /*
         * 删除拼接好的字符串中最后一个','
         * 得到  insert into employee( emp_code , emp_name
         */
        sb.delete(sb.toString().lastIndexOf(","),sb.length());
        sb.append(sqlw);
        sb.append(";");
        sqlBasicInfo.setUpdateSql(sb.toString());
        return sb.toString();
    }

    private  String dealDecorate(String str){
        return "`"+str.toLowerCase()+"`";
    }

    /**
     * 获取表名
     * @param sqlBasicInfo
     * @return
     */
    private String getTableNameInfo(SqlBasicInfo sqlBasicInfo){
        if(sqlBasicInfo==null){
            logger.error("获取表名失败\n");
            return "";
        }
        return " " +dealDecorate(sqlBasicInfo.getSchema())+"."+dealDecorate(sqlBasicInfo.getTableName())+" ";
    }
}
