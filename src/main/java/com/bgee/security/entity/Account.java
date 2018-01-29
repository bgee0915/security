package com.bgee.security.entity;

import java.util.Date;

public class Account {
    private Integer id;
    private String account;
    private String pass;
    private String tel;
    private Integer sex;
    private String headimg;
    private Integer status;
    private Date createDate;
    private String createBy;
    private Date modifyDate;
    private String modifyBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", tel='" + tel + '\'' +
                ", sex=" + sex +
                ", headimg='" + headimg + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", modifyDate=" + modifyDate +
                ", modifyBy='" + modifyBy + '\'' +
                '}';
    }
}
