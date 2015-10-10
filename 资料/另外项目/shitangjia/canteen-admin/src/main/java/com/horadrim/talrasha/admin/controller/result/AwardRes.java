package com.horadrim.talrasha.admin.controller.result;

import com.horadrim.talrasha.common.response.json.SuccessJsonRes;

/**
 * Created by Administrator on 2015/6/11.
 */
public class AwardRes extends SuccessJsonRes {
    private int award;
    private int awardCode;

    public int getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(int awardCode) {
        this.awardCode = awardCode;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }
}
