package com.mrwish.mybox.model;

import java.io.Serializable;
import java.util.List;

public class BaseModel implements Serializable {

    private String type;
    private String msg_name;
    private String msg_mag;
    private String msg_time;
    private String msg_num;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg_name() {
        return msg_name;
    }

    public void setMsg_name(String msg_name) {
        this.msg_name = msg_name;
    }

    public String getMsg_mag() {
        return msg_mag;
    }

    public void setMsg_mag(String msg_mag) {
        this.msg_mag = msg_mag;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }

    public String getMsg_num() {
        return msg_num;
    }

    public void setMsg_num(String msg_num) {
        this.msg_num = msg_num;
    }
}
