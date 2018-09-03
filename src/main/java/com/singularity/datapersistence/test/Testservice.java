package com.singularity.datapersistence.test;

import com.singularity.datapersistence.common.InitSqlTool;
import com.singularity.datapersistence.db.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Testservice {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private InitSqlTool initSqlTool;

    public void save() {
        Student student=new Student();
        student.setBirthday(new Date());
        student.setName("tch");
        student.setYear(1);
        /*List<User> users = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<User>(User.class));
        User user= users.get(0);
        System.out.println("jdbcTemplate2 -->"+user.toString()+"\n");*/
    }

    public void query(){
        String sql ="select * from user ";
        List<User> users = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
        User user= users.get(0);
        System.out.println("jdbcTemplate -->"+user.toString()+"\n");
    }

    public void test() throws Exception {
        initSqlTool.init();
    }

}
