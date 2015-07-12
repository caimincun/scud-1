package cn.scud.main.evaluate.model;

/**
 * Created by Administrator on 2015/7/7.
 * 评价信息表
 */
public class Evaluate {

    private int evaluateId;
    //评对象者ID
    private String evaluateUsken;
    //被评价者Id
    private String evaluateToUsken;
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

    public String getEvaluateUsken() {
        return evaluateUsken;
    }

    public void setEvaluateUsken(String evaluateUsken) {
        this.evaluateUsken = evaluateUsken;
    }

    public String getEvaluateToUsken() {
        return evaluateToUsken;
    }

    public void setEvaluateToUsken(String evaluateToUsken) {
        this.evaluateToUsken = evaluateToUsken;
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
                ", evaluateUsken='" + evaluateUsken + '\'' +
                ", evaluateToUsken='" + evaluateToUsken + '\'' +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", evaluateTime='" + evaluateTime + '\'' +
                '}';
    }
}
