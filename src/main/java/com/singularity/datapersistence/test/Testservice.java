package com.singularity.datapersistence.test;

import com.singularity.datapersistence.db.Student;
import com.singularity.datapersistence.service.out.DaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
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
    protected DaoServiceInterface daoService;

    @Transient
    public void save() throws Exception {
        long saveOneStart=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
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
        long saveOneEnd=System.currentTimeMillis();
        System.out.println("saveOne："+(saveOneEnd-saveOneStart));
    }

    @Transient
    public void saveBatch() throws Exception {
        List list=new ArrayList();
        long saveOneStart=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            Student student=new Student();
            student.setBirthday(new Date());
            student.setCreateTime(new Date());
            student.setLastModifyTime(new Date());
            student.setBirthday(new Date());
            student.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            student.setName("tch");
            student.setYear(1);
            list.add(student);
        }
        long saveOneEnd=System.currentTimeMillis();
        daoService.insertBatch(list);
        System.out.println("saveBatch："+ (saveOneEnd-saveOneStart));

    }

    public void update() throws Exception {
        Student student=new Student();
        student.setBirthday(new Date());
        student.setCreateTime(new Date());
        student.setLastModifyTime(new Date());
        student.setBirthday(new Date());
        student.setId("0d85d6341c574d97b623e00d928c6f4d");
        student.setName("tch123");
        student.setYear(1);
        daoService.update(student);
    }

    public void query(){
        String sql ="select * from student limit 10 ";
        daoService.query(sql,Student.class);
    }

}
