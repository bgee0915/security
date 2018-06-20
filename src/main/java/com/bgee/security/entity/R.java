package com.bgee.security.entity;

public class R {
    /** 1:true  0:false  */
    private Integer ret;
    private Boolean success;
    private Object data;
    private String msg;

    public R(){}

    public R(Integer ret, Object data,Boolean success,String msg){
        this.ret = ret;
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public R(Integer ret,String msg){
        this.ret = ret;
        this.msg = msg;
    }

    public R(Integer ret, String msg, Object data){
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }

    public R(Integer ret, String msg, Boolean success){
        this.ret = ret;
        this.msg = msg;
        this.success = success;
    }
    public R(Integer ret, Object data ,Boolean success){
        this.ret = ret;
        this.success = success;
        this.data = data;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "R{" +
                "ret=" + ret +
                ", success=" + success +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
