package HealthDiary.DataBase.services;

import HealthDiary.DataBase.dao.QuestionDao;
import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbQuestion;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.utils.TxFixAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(
            DiaryService.class);

    private QuestionDao qd;
    private DbDiary diary;

    public QuestionService(int diaryId){
        this.diary = new DiaryService().findDiary(diaryId);
    }

    public DbQuestion findQuestion(int id){
        this.qd = new QuestionDao();

        logger.debug("try find question {} for diary {} in database", id, diary);

        DbQuestion question = qd.findById(id);
        qd.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", question.toString());

        return question;
    }

    public void addQuestion(String questText, int pos){
        this.qd = new QuestionDao(new DbQuestion(questText));

        logger.debug("try add question for diary \"{}\" in database", diary);


        try {
            qd.addQuestion(this.diary.getId(), pos);
            qd.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            qd.fixTx(TxFixAction.ROLLBACK);

            logger.error("Can't add question for diary \"{}\"", diary, e);
            throw e;
        }
    }
}
