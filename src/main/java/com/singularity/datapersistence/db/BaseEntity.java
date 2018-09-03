package com.singularity.datapersistence.db;

import com.singularity.datapersistence.annotation.Id;
import com.singularity.datapersistence.annotation.Ignore;
import com.singularity.datapersistence.enums.ExistEnum;

import java.util.Date;

/**
 * 基类
 */
public class BaseEntity {

    @Id
    private String id;

    private Date createTime;//创建时间
    private String createPerson;//创建人名
    private String createPersonId;//创建人

    private String lastModifyPerson;//最后修改人名
    private String lastModifyPersonId;//最后修改人
    private Date lastModifyTime;//最后修改时间

    private ExistEnum isDel;//是否删除

    @Ignore
    private String isDelName;//是否删除名称


    public BaseEntity() {
        this.isDel=ExistEnum.exist;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getLastModifyPerson() {
        return lastModifyPerson;
    }

    public void setLastModifyPerson(String lastModifyPerson) {
        this.lastModifyPerson = lastModifyPerson;
    }

    public String getLastModifyPersonId() {
        return lastModifyPersonId;
    }

    public void setLastModifyPersonId(String lastModifyPersonId) {
        this.lastModifyPersonId = lastModifyPersonId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public ExistEnum getIsDel() {
        return isDel;
    }

    public void setIsDel(ExistEnum isDel) {
        this.isDel = isDel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsDelName() {
        return this.isDel.getText();
    }
}
