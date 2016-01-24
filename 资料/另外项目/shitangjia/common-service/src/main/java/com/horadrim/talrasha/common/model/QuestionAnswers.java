package com.horadrim.talrasha.common.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name = "qc_question_answers")
public class QuestionAnswers extends AbstractDomain {

    private int questionId;

    private String value;

    @Column(name="value",length = 100)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name="question_id",length = 5)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

//    @Override
//    public String toString() {
//        return "Answers{" +
//                "questionId=" + questionId +
//                ", value='" + value + '\'' +
//                '}';
//    }
}
