package cn.scud.commoms.response;



/**
 * Created by Victor on 2014/4/12.
 */
public class ErrorJsonRes extends OperatorResponse {

    public ErrorJsonRes(int code,String msg) {
        setRespStatus(new AbstractJsonRes(code,msg));
    }


}
