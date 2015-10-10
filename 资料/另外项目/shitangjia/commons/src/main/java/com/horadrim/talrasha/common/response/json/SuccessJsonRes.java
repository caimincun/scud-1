package com.horadrim.talrasha.common.response.json;

import com.horadrim.talrasha.common.CodeDefined;

/**
 * Created by Victor on 2014/4/12.
 */
public class SuccessJsonRes extends AbstractJsonRes {
    public SuccessJsonRes() {
        super(CodeDefined.SUCCESS, CodeDefined.getMessage(CodeDefined.SUCCESS));
    }
}
