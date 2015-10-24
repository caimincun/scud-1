package com.horadrim.talrasha.common.service.dto;

import com.horadrim.talrasha.common.model.QuestionAnswers;

import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public class QuestionPojo {

    private int id;
    private String title;
    private String questionType;
    private int canteenId;
    private List<QuestionAnswers> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public List<QuestionAnswers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswers> answers) {
        this.answers = answers;
    }
}
