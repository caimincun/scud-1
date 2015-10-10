package com.horadrim.talrasha.common.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table( name ="qc_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Question extends AbstractDomain{

    private String title;

    private String questionType;

    private int canteenId;

    private int questionCategoryId;

    @Column(name = "question_category_id")
    public int getQuestionCategoryId() {
        return questionCategoryId;
    }

    @Column(name="title",length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="question_type",length = 50)
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }


    public void setQuestionCategoryId(int questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", questionType='" + questionType + '\'' +

                '}';
    }
}
