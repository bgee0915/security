package com.bgee.security.entity;

public class Img {
    public static final Integer IMG_TYPE_ICON = 1;
    public static final Integer IMG_TYPE_BANNER = 2;

    public static final Integer IMG_STATUS_Y = 1;
    public static final Integer IMG_STATUS_N = 0;


    private Integer id;

    private String url;

    /** 1:icon 2:banner 3:xxx */
    private Integer type;

    /** 域 */
    private String realm;

    /** 备注 */
    private String remark;

    /** 1:可用 0:不可用 */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", realm='" + realm + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
