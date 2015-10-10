package com.horadrim.talrasha.site.controller.result;

import com.horadrim.talrasha.common.response.json.SuccessJsonRes;

/**
 * Created by Administrator on 2015/6/4.
 * 修改购物车里面商品数量返回结果
 */
public class ChangeCartRes extends SuccessJsonRes {
    private int count;
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
