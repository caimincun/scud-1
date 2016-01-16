package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.model.Other;

/**
 * Created by Administrator on 2015/6/11.
 */
public interface OtherService extends GenericService<Other,Integer>{

    /**
     * 添加问题和问题选择答案
     * @param title
     * @param type
     * @param A
     * @param B
     * @param C
     * @param D
     */
  //  void addQuestionAndAnswers(String title, String type, String A, String B, String C, String D);

    public void addOther(Other other);


}
