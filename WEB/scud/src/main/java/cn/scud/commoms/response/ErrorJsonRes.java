package cn.scud.commoms.response;


import cn.scud.commoms.CodeDefined;

/**
 * Created by Victor on 2014/4/12.
 */
public class ErrorJsonRes extends OperatorResponse {

    public ErrorJsonRes(int code,String msg) {
        setRespStatus(new AbstractJsonRes(code,msg));
    }


}
