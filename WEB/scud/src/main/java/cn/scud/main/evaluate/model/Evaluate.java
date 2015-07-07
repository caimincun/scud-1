package cn.scud.main.evaluate.model;

/**
 * Created by Administrator on 2015/7/7.
 * 评价信息表
 */
public class Evaluate {

    private int evaluateId;
    //评对象者ID
    private int evaluateOwnerId;
    //被评价者Id
    private int evaluateToUserId;
    //+评价内容
    private String evaluateContent;
    //评价时间
    private String evaluateTime;

    public int getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(int evaluateId) {
        this.evaluateId = evaluateId;
    }

    public int getEvaluateOwnerId() {
        return evaluateOwnerId;
    }

    public void setEvaluateOwnerId(int evaluateOwnerId) {
        this.evaluateOwnerId = evaluateOwnerId;
    }

    public int getEvaluateToUserId() {
        return evaluateToUserId;
    }

    public void setEvaluateToUserId(int evaluateToUserId) {
        this.evaluateToUserId = evaluateToUserId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "evaluateId=" + evaluateId +
                ", evaluateOwnerId=" + evaluateOwnerId +
                ", evaluateToUserId=" + evaluateToUserId +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", evaluateTime='" + evaluateTime + '\'' +
                '}';
    }
}
