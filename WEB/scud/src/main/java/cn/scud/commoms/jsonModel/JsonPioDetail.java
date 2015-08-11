package cn.scud.commoms.jsonModel;

/**
 * Created by Administrator on 2015/8/7.
 */
public class JsonPioDetail {
    public int status;
    public String message;
    private JsonPioDetailCon poi;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonPioDetailCon getPoi() {
        return poi;
    }

    public void setPoi(JsonPioDetailCon poi) {
        this.poi = poi;
    }

    @Override
    public String toString() {
        return "JsonPioDetail{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", poi=" + poi +
                '}';
    }
}
