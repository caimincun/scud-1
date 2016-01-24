package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.Question;
import com.horadrim.talrasha.common.service.dto.QuestionPojo;

import java.util.List;


/**
 * Created by Administrator on 2015/6/11.
 */
public interface QuestionService extends GenericService<Question,Integer>{

    /**
     * 添加问题和问题选择答案
     * @param title
     * @param type
     * @param A
     * @param B
     * @param C
     * @param D
     */
    void addQuestionAndAnswers(int canteenId,int questionCategoryId,String title,String type,String A,String B,String C,String D);
//
//    /**
//     * 生成统计
//     */
//    List<QuestionPojo> chart();

    List<QuestionPojo> listByCanteenId(int canteenId,int categoryId);
}
