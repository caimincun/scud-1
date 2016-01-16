package com.horadrim.talrasha.site.controller.result;

import com.horadrim.talrasha.common.response.json.SuccessJsonRes;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/7/24.
 */
public class VegCheckConfirmRes extends SuccessJsonRes {
    private BigDecimal totalPrice;
    private float safeAmount;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getSafeAmount() {
        return safeAmount;
    }

    public void setSafeAmount(float safeAmount) {
        this.safeAmount = safeAmount;
    }
}
