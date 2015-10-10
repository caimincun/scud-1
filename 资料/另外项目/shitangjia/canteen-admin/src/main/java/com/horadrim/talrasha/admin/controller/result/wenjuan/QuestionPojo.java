package com.horadrim.talrasha.admin.controller.result.wenjuan;

import java.util.List;

/**
 * Created by Administrator on 2015/6/24.
 */
public class QuestionPojo {
    private String title;
    private int id;
    private int totalCount;
    private int questionType;
    private String xAxis;
    private String data;

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private List<AnswerPojo> answerPojos;

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<AnswerPojo> getAnswerPojos() {
        return answerPojos;
    }

    public void setAnswerPojos(List<AnswerPojo> answerPojos) {
        this.answerPojos = answerPojos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
