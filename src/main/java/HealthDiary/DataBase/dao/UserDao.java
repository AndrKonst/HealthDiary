package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import HealthDiary.DataBase.utils.SessionFactoryUtil;
import HealthDiary.DataBase.utils.TxFixAction;
import HealthDiary.exceptions.NoDataFound;

public class UserDao implements DML {

    private Session session;
    private Transaction tx;

    public UserDao(){
        openTx();
    }

    @Override
    public void openTx() {
        this.session = SessionFactoryUtil.getSessionFactory().openSession();
        this.tx = session.beginTransaction();
    }

    public void fixTx(TxFixAction act) {
        if (act == TxFixAction.COMMIT) {
            this.tx.commit();
            this.session.close();
        } else if (act == TxFixAction.ROLLBACK) {
            this.tx.rollback();
            this.session.close();
        }
    }

    @Override
    public void insert(Object obj) {
        DbUser dbUser = (DbUser) obj;
        this.session.persist(dbUser);
    }

    @Override
    public void update(Object obj) {
        // CHeck existence
        DbUser dbUser = (DbUser) findById(((DbUser) obj).getId());

        session.merge((DbUser) obj);
    }

    @Override
    public void delete(Object obj) {
        // CHeck existence
        DbUser dbUser = (DbUser) findById(((DbUser) obj).getId());

        session.remove(dbUser);
    }

    public Object findById(Object id) {
        Long user_id = (Long) id;

        DbUser dbUser = this.session.get(DbUser.class, user_id);
        if (dbUser == null){
            throw new NoDataFound("No user with id \"" + id + "\"");
        }

        return dbUser;
    }
}
