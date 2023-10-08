package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.utils.TxFixAction;

public interface Tx {

    public void openTx();
    public void fixTx(TxFixAction act);
}
