package HealthDiary.DataBase.services;

import HealthDiary.DataBase.dao.DiaryFillingDao;
import HealthDiary.DataBase.models.DbDiaryFilling;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.utils.TxFixAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiaryFillingService {
    private static final Logger logger = LoggerFactory.getLogger(
            DiaryFillingService.class);

    private DiaryFillingDao dfd;

    public DbDiaryFilling findDiaryFilling(Long id){
        this.dfd = new DiaryFillingDao();

        logger.debug("try find diary_filling {} in database", id);

        dfd.findById(id);
        dfd.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", dfd.getDf().toString());

        return dfd.getDf();
    }

    public DbDiaryFilling findDiaryFilling(DbUser user, Boolean diaryCreationFl){
        this.dfd = new DiaryFillingDao(user, diaryCreationFl);

        logger.debug("try find diary_filling for user {} in database", user);

        dfd.findLastDf4User();
        dfd.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", dfd.getDf().toString());

        return dfd.getDf();
    }

    public void setFilling(DbUser user, Boolean diaryCreationFl) {
        DiaryFillingDao dfd = new DiaryFillingDao(user, diaryCreationFl);

        logger.debug("Set diary_filling for user \"{}\" in database", user);

        try {
            dfd.setFilling();
            dfd.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            dfd.fixTx(TxFixAction.ROLLBACK);

            logger.error("Cant create diary_filling for user \"{}\"", user, e);
            throw e;
        }

    }
}
