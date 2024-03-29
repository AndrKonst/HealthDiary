package HealthDiary.DataBase.services;

import HealthDiary.DataBase.dao.*;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.utils.TxFixAction;
import HealthDiary.TG.HealthDiaryTGBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(
            UserService.class);

    private UserDao ud;

    public DbUser findUser(Long id){
        this.ud = new UserDao();

        logger.debug("try find User {} in database", id);

        DbUser usr = ud.findById(id);
        ud.fixTx(TxFixAction.COMMIT);

        logger.debug("Found {}", usr.toString());

        return usr;
    }

    public void insertUser(DbUser dbUser) {
        this.ud = new UserDao(dbUser);

        logger.debug("insert User {} in database", dbUser.toString());

        try {
            ud.insert();
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            ud.fixTx(TxFixAction.ROLLBACK);

            logger.error("Cant insert user {}", dbUser, e);
            throw e;
        }

    }

    public void deleteUser(DbUser dbUser) {
        this.ud = new UserDao(dbUser);

        logger.debug("delete User {} from database", dbUser.toString());

        try {
            ud.delete();
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e){
            ud.fixTx(TxFixAction.ROLLBACK);

            logger.error("Cant delete user {}", dbUser, e);
            throw e;
        }
    }

    public void updateUser(DbUser dbUser) {
        this.ud = new UserDao(dbUser);

        logger.debug("update User {} in database", dbUser.toString());

        try {
            ud.update();
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e){
            ud.fixTx(TxFixAction.ROLLBACK);

            logger.error("Cant update user {}", dbUser, e);
            throw e;
        }
    }
}
