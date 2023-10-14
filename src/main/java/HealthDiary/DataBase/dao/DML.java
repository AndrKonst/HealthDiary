package HealthDiary.DataBase.dao;

public interface DML extends Tx {

    public void insert(Object obj);
    public void update(Object obj);
    public void delete(Object obj);
}
