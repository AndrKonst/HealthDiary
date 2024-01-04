package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.utils.SessionFactoryUtil;
import HealthDiary.DataBase.utils.TxFixAction;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDao implements Tx {

    private Session session;
    private Transaction tx;

    public BaseDao(){
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

    public Session getSession() {
        return session;
    }
    public Transaction getTx() {
        return tx;
    }
}
