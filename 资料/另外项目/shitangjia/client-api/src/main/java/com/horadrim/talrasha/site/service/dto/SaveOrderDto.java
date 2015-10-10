package com.horadrim.talrasha.site.service.dto;

import com.horadrim.talrasha.common.model.VegetableOrder;

/**
 * Created by Administrator on 2015/7/24.
 * 保存订单返回 状态
 */
public class SaveOrderDto {

    private int ret;            //状态码  0 添加成功 1 被冻结金额不足
    private VegetableOrder order;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public VegetableOrder getOrder() {
        return order;
    }

    public void setOrder(VegetableOrder order) {
        this.order = order;
    }
}
