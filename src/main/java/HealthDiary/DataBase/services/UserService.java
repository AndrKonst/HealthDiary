package HealthDiary.DataBase.services;

import HealthDiary.DataBase.dao.*;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.utils.TxFixAction;

public class UserService {

    private UserDao ud;

    public DbUser findUser(Long id){
        this.ud = new UserDao();

        Object usr = ud.findById(id);
        ud.fixTx(TxFixAction.COMMIT);

        return (DbUser) usr;
    }

    public void insertUser(DbUser dbUser) {
        this.ud = new UserDao();

        try {
            ud.insert(dbUser);
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            ud.fixTx(TxFixAction.ROLLBACK);
            throw e;
        }

    }

    public void deleteUser(DbUser dbUser) {
        this.ud = new UserDao();

        try {
            ud.delete(dbUser);
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e){
            ud.fixTx(TxFixAction.ROLLBACK);
            throw e;
        }
    }

    public void updateUser(DbUser dbUser) {
        this.ud = new UserDao();

        try {
            ud.update(dbUser);
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e){
            ud.fixTx(TxFixAction.ROLLBACK);
            throw e;
        }
    }
}
