package cn.scud.commoms.jsonModel;

import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
public class JsonPioSearch {
    public int status;
    public int total;
    public int size;
    public List<JsonPioContent> contents;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<JsonPioContent> getContents() {
        return contents;
    }

    public void setContents(List<JsonPioContent> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "JsonPioSearch{" +
                "status=" + status +
                ", total=" + total +
                ", size=" + size +
                ", contents=" + contents +
                '}';
    }
}
