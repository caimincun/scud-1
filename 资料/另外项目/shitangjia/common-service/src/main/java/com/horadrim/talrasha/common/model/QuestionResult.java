package com.horadrim.talrasha.common.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name = "qc_question_result")
public class QuestionResult extends AbstractDomain {

    private int questionId;

    private int answersId;

    private int userId;

    @Column(name = "user_id",length = 5)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "question_id",length = 5)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Column(name = "answers_id",length = 5)
    public int getAnswersId() {
        return answersId;
    }

    public void setAnswersId(int answersId) {
        this.answersId = answersId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "questionId=" + questionId +
                ", answersId=" + answersId +
                ", userId=" + userId +
                '}';
    }
}
