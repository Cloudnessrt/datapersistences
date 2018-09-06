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
    //创建时间
    private Date createTime;
    //创建人名
    private String createPerson;
    //创建人
    private String createPersonId;
    //最后修改人名
    private String lastModifyPerson;
    //最后修改人
    private String lastModifyPersonId;
    //最后修改时间
    private Date lastModifyTime;
    //是否删除
    private ExistEnum isDel;
    //是否删除名称
    @Ignore
    private String isDelName;


    public BaseEntity() {
        this.isDel=ExistEnum.exist;
    }

    public Date getCreateTime() {
        if (createTime == null)
        {
            return null;
        }
        return (Date)createTime.clone();
    }

    public void setCreateTime(Date createTime) {
        if (createTime == null)
        {
            this.createTime = null;
        } else {
            this.createTime = (Date)createTime.clone();
        }
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
        if (lastModifyTime == null)
        {
            return null;
        }
        return (Date)lastModifyTime.clone();
    }

    public void setLastModifyTime(Date lastModifyTime) {
        if (lastModifyTime == null)
        {
            this.lastModifyTime = null;
        } else {
            this.lastModifyTime = (Date)lastModifyTime.clone();
        }
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
