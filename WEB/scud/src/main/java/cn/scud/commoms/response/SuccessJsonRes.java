package cn.scud.commoms.response;


import cn.scud.commoms.CodeDefined;

/**
 * Created by Victor on 2014/4/12.
 */
public class SuccessJsonRes extends OperatorResponse {

    public SuccessJsonRes() {
        setRespStatus(new AbstractJsonRes(CodeDefined.SUCCESS, CodeDefined.getMessage(CodeDefined.SUCCESS)));
    }


}
