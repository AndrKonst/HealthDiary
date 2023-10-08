package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.User;
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
        User user = (User) obj;
        this.session.persist(user);
    }

    @Override
    public void update(Object obj) {
        // CHeck existence
        User user = (User) findById(((User) obj).getId());

        session.merge((User) obj);
    }

    @Override
    public void delete(Object obj) {
        // CHeck existence
        User user = (User) findById(((User) obj).getId());

        session.remove(user);
    }

    public Object findById(Object id) {
        String user_id = (String) id;

        User user = this.session.get(User.class, user_id);
        if (user == null){
            throw new NoDataFound("No user with id \"" + id + "\"");
        }

        return user;
    }
}
