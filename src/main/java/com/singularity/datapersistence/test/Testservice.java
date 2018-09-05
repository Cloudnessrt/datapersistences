package com.singularity.datapersistence.test;

import com.singularity.datapersistence.common.InitSqlTool;
import com.singularity.datapersistence.db.Student;
import com.singularity.datapersistence.service.out.DaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    protected DaoServiceInterface daoService;

    public void save() throws Exception {
        Student student=new Student();
        student.setBirthday(new Date());
        student.setCreateTime(new Date());
        student.setLastModifyTime(new Date());
        student.setBirthday(new Date());
        student.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        student.setName("tch");
        student.setYear(1);
        daoService.insert(student);
    }

    public void update() throws Exception {
        Student student=new Student();
        student.setBirthday(new Date());
        student.setCreateTime(new Date());
        student.setLastModifyTime(new Date());
        student.setBirthday(new Date());
        student.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        student.setName("tch");
        student.setYear(1);
        daoService.update(student);
    }

    public void query(){
        String sql ="select * from student ";
        List<Student> users = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Student>(Student.class));
        Student user= users.get(0);
        System.out.println("jdbcTemplate -->"+user.toString()+"\n");
    }

    public void test() throws Exception {
        initSqlTool.init();
    }

}
