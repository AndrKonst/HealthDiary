package HealthDiary.DataBase.services;

import HealthDiary.DataBase.dao.DiaryDao;
import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.utils.TxFixAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiaryService {

    private static final Logger logger = LoggerFactory.getLogger(
            DiaryService.class);

    private DiaryDao dd;
    private DbUser user;

    public DiaryService(DbUser user){
        this.user = user;
    }
    public DiaryService(){}

    public DbDiary findDiary(int id){
        this.dd = new DiaryDao();

        logger.debug("try find diary {} in database", id);

        DbDiary diary = dd.findById(id);
        dd.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", diary.toString());

        return diary;
    }

    public DbDiary findDiary(String diary_name){
        this.dd = new DiaryDao();

        logger.debug("try find diary {} in database", diary_name);

        DbDiary diary = dd.findByName(diary_name);
        dd.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", diary.toString());

        return diary;
    }

    public void createDiary(String diary_name) {
        DbDiary diary = new DbDiary(diary_name);

        logger.debug("Create diary \"{}\" by user {} in database", diary.getName(), this.user);

        this.dd = new DiaryDao(diary);

        try {
            dd.createDiary(user);
            dd.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            dd.fixTx(TxFixAction.ROLLBACK);

            logger.error("Cant create diary \"{}\"", diary.getName(), e);
            throw e;
        }

    }

    public void closeDiary(int diary_id) {
        DbDiary diary = findDiary(diary_id);

        logger.debug("Close diary \"{}\" by user {} in database", diary.getName(), this.user);

        this.dd = new DiaryDao(diary);

        try {
            dd.closeDiary();
            dd.fixTx(TxFixAction.COMMIT);

            logger.debug("Diary {} closed", diary_id);
        } catch (Exception e) {
            dd.fixTx(TxFixAction.ROLLBACK);

            logger.error("Cant close diary \"{}\"", diary.getName(), e);
            throw e;
        }

    }
}
