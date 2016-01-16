package com.horadrim.talrasha.site.controller.request;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/4.
 */
public class AddOrderReq {
    private Date date;
    private int time;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
