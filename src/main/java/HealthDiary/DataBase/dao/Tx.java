package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.utils.TxFixAction;

public interface Tx {

    void openTx();
    void fixTx(TxFixAction act);
}
