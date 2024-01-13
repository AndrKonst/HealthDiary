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

        qd.findById(id);
        qd.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", qd.getQuestion().toString());

        return qd.getQuestion();
    }

    public DbQuestion findQuestionByPos(int pos){
        this.qd = new QuestionDao();

        logger.debug("try find question with pos {} for diary {} in database", pos, diary);

        qd.findByPos(this.diary.getId(), pos);
        qd.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", qd.getQuestion().toString());

        return qd.getQuestion();
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

    public static int getLastQuestPos(int diaryId){
        QuestionDao qd = new QuestionDao();

        int res;
        try {
            res = qd.getLastQuestPos(diaryId);
            qd.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            qd.fixTx(TxFixAction.ROLLBACK);

            logger.error("Can't find last quest pos for diary {}", diaryId, e);
            throw e;
        }
        return res;
    }
}
