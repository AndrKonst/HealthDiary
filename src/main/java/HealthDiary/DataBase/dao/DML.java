package HealthDiary.DataBase.dao;

public interface DML extends Tx {

    void insert();
    void update();
    void delete();
}
