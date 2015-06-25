package cn.scud.commoms.response;

import java.util.List;

/**
 * Created by Administrator on 2014/11/13.
 */
public class ListSucRes extends SuccessJsonRes {
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
