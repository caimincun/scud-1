package cn.scud.commoms.response;

/**
 * Created by victor on 14-4-11.
 */
public  class AbstractJsonRes {

    private int result;

    private String msg;

    public AbstractJsonRes(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
