package HealthDiary.DataBase.services;

import HealthDiary.DataBase.dao.*;
import HealthDiary.DataBase.models.User;
import HealthDiary.DataBase.utils.TxFixAction;

import java.util.List;

public class UserService {

    private UserDao ud;

    public User findUser(String id){
        this.ud = new UserDao();

        Object usr = ud.findById(id);
        ud.fixTx(TxFixAction.COMMIT);

        return (User) usr;
    }

    public void insertUser(User user) {
        this.ud = new UserDao();

        try {
            ud.insert(user);
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e) {
            ud.fixTx(TxFixAction.ROLLBACK);
            throw e;
        }

    }

    public void deleteUser(User user) {
        this.ud = new UserDao();

        try {
            ud.delete(user);
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e){
            ud.fixTx(TxFixAction.ROLLBACK);
            throw e;
        }
    }

    public void updateUser(User user) {
        this.ud = new UserDao();

        try {
            ud.update(user);
            ud.fixTx(TxFixAction.COMMIT);
        } catch (Exception e){
            ud.fixTx(TxFixAction.ROLLBACK);
            throw e;
        }
    }
}
