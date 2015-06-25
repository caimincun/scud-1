package cn.scud.commoms.response;

/**
 * Created by Administrator on 2014/12/26.
 */
public class GetObjSucRes extends SuccessJsonRes {
    private Object Data ;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
