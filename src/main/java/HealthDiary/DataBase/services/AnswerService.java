package HealthDiary.DataBase.services;

import HealthDiary.DataBase.dao.AnswerDao;
import HealthDiary.DataBase.dao.QuestionDao;
import HealthDiary.DataBase.models.DbAnswer;
import HealthDiary.DataBase.models.DbQuestion;
import HealthDiary.DataBase.utils.TxFixAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnswerService {
    private static final Logger logger = LoggerFactory.getLogger(
            DiaryService.class);

    private DbQuestion question;

    private AnswerDao ad;

    public AnswerService(int diaryId, int questionPos){
        question = new QuestionService(diaryId).findQuestionByPos(questionPos);
    }

    public DbAnswer findAnswer(int id){
        ad = new AnswerDao();
        ad.findById(id);
        ad.fixTx(TxFixAction.COMMIT);

        return ad.getAnswer();
    }

    public void addAnswer(String answText, int pos){
        ad = new AnswerDao(answText);

        try {
            ad.addAnswer(question.getQuestionId(), pos);
            ad.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            ad.fixTx(TxFixAction.ROLLBACK);

            logger.error("Can't add answer for question \"{}\"", question, e);
            throw e;
        }
    }

    public static int getLastAnswPos(int questionId){
        AnswerDao ad = new AnswerDao();

        int res;
        try {
            res = ad.getLastAnswerPos(questionId);
            ad.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            ad.fixTx(TxFixAction.ROLLBACK);

            logger.error("Can't find last answer pos for question {}", questionId, e);
            throw e;
        }
        return res;
    }
}
