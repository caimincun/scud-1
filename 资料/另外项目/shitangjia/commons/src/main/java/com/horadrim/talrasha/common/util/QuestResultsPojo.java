package com.horadrim.talrasha.common.util;

/**
 * Created by Administrator on 2015/6/11.
 */
public class QuestResultsPojo {
    private int id;
    private int questionId;
    private String answerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionid(int questionId) {
        this.questionId = questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    //    @Override
//    public String toString() {
//        return "QuestAnswerPojo{" +
//                "questionid=" + questionId +
//                ", answerid=" + answerid +
//                '}';
//    }
}
