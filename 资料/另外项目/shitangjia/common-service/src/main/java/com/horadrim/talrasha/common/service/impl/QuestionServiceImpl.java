package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.QuestionAnswersDao;
import com.horadrim.talrasha.common.dao.QuestionDao;
import com.horadrim.talrasha.common.model.Question;
import com.horadrim.talrasha.common.model.QuestionAnswers;
import com.horadrim.talrasha.common.service.QuestionService;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.service.dto.QuestionPojo;
import com.horadrim.talrasha.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service("questionService")
public class QuestionServiceImpl extends GenericServiceImpl<Question,Integer> implements QuestionService {
    @Resource
    private QuestionDao questionDao;
    @Resource
    private QuestionAnswersDao questionAnswersDao;
    @Override
    protected GenericDao<Question, Integer> getGenericDao() {
        return questionDao;
    }

    @Override
    public void addQuestionAndAnswers(int canteenId,int questionCategoryId,String title, String questionType, String A, String B, String C, String D) {
        Question question = new Question();
        question.setTitle(title);
        question.setQuestionType(questionType);
        question.setCanteenId(canteenId);
        question.setQuestionCategoryId(questionCategoryId);
        question = questionDao.save(question);
        QuestionAnswers answers;
        if(!StringUtils.isEmpty(A)){
             answers = new QuestionAnswers();
            answers.setValue(A);
            answers.setQuestionId(question.getId());
            questionAnswersDao.save(answers) ;
        }
        if(!StringUtils.isEmpty(B)){
             answers = new QuestionAnswers();
            answers.setValue(B);
            answers.setQuestionId(question.getId());
            questionAnswersDao.save(answers) ;
        }
        if(!StringUtils.isEmpty(C)){
             answers = new QuestionAnswers();
            answers.setValue(C);
            answers.setQuestionId(question.getId());
            questionAnswersDao.save(answers) ;
        }
        if(!StringUtils.isEmpty(D)){
             answers = new QuestionAnswers();
            answers.setValue(D);
            answers.setQuestionId(question.getId());
            questionAnswersDao.save(answers) ;
        }

    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public List<QuestionPojo> chart() {
//        String[] itemStr ={"A","B","C","D","E","F"};
//        final String queryQuestion = "SELECT count(r.id) as count,q.* FROM qc_question_result r" +
//                " LEFT JOIN qc_question q ON q.id = r.question_id GROUP BY r.question_id ";
//        final String queryAnswer = "SELECT count(*) as icount,a.* FROM qc_question_result r " +
//                "LEFT JOIN qc_question_answers a ON r.answers_id=a.id WHERE r.question_id = ? " +
//                " GROUP BY r.answers_id";
//        List<Map<String,Object>> questions = listFieldBySQL(queryQuestion,null);
//        List<QuestionPojo> pojos = new LinkedList<>();
//        for (Map<String,Object> question : questions){
//            QuestionPojo pojo = new QuestionPojo();
//            int qid = (int) question.get("id");
//            Double count = Double.valueOf(question.get("count").toString());
//            pojo.setId(qid);
//            pojo.setQuestionType(Integer.parseInt((String) question.get("question_type")));
//            pojo.setTitle((String) question.get("title"));
//            pojo.setTotalCount(count.intValue());
//
//            List<Map<String,Object>> answers = listFieldBySQL(queryAnswer,new Object[]{qid});
//            List<AnswerPojo> answerPojos = new LinkedList<>();
//            String xAlias = "";
//            String data = "";
//            for (int i = 0 ; i<answers.size();i++){
//                Map<String,Object> answer = answers.get(i);
//                Double icount = Double.valueOf(answer.get("icount").toString());
//                AnswerPojo answerPojo = new AnswerPojo();
//                answerPojo.setItem((String) answer.get("value"));
//                answerPojo.setItemStr(itemStr[i]);
//                answerPojo.setNum(icount.intValue());
//                xAlias+=",\""+itemStr[i]+"\"";
//                data+=","+answerPojo.getNum();
//                answerPojos.add(answerPojo);
//            }
//            pojo.setxAxis(xAlias.substring(1));
//            pojo.setData(data.substring(1));
//            pojo.setAnswerPojos(answerPojos);
//            pojos.add(pojo);
//        }
//        return pojos;
//    }

    @Override
    public List<QuestionPojo> listByCanteenId(int canteenId,int categoryId) {
        final String queryQuestion = "FROM Question WHERE canteenId="+canteenId+
                " AND questionCategoryId="+categoryId+" ORDER BY id ASC";
        final String queryAnswers = "FROM QuestionAnswers WHERE questionId=? ORDER BY id ASC";
        List<Question> questions = questionDao.list(queryQuestion,null);
        List<QuestionPojo> pojos = new ArrayList<>();
        for (Question question:questions){
            QuestionPojo pojo = new QuestionPojo();
            pojo.setId(question.getId());
            pojo.setCanteenId(question.getCanteenId());
            pojo.setQuestionType(question.getQuestionType());
            pojo.setTitle(question.getTitle());
            pojo.setAnswers(questionAnswersDao.list(queryAnswers,new Object[]{question.getId()}));
            pojos.add(pojo);
        }
        return pojos;
    }
}
