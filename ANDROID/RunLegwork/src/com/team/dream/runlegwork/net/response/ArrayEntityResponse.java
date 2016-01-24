package com.team.dream.runlegwork.net.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 秋平 on 2015/9/23.
 */
public class ArrayEntityResponse<T> extends OpteratorResponse {
    private List<T> data;

    public List<T> getData() {
        if (data == null)
            data = new ArrayList<T>();
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
