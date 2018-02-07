package com.bgee.security.entity;

public class Authz {
    private Integer id;
    private Integer pid;
    private String name;
    private String keys;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Authz{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", keys='" + keys + '\'' +
                ", status=" + status +
                '}';
    }
}
