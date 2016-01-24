package com.team.dream.runlegwork.net.response;

/**
 * Created by 秋平 on 2015/9/23.
 */
public class EntityResponse<T> extends OpteratorResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
