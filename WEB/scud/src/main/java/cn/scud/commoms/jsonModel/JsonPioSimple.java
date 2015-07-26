package cn.scud.commoms.jsonModel;

/**
 * Created by Administrator on 2015/7/20.
 * 这个model 对于 pio 添加和修改的返回信息都能解析
 * {"status":0,"id":1077051852,"message":"成功"}
 */
public class JsonPioSimple {

    private int status;
    private int id;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonPioSimple{" +
                "status=" + status +
                ", id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
