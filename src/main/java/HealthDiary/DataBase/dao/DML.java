package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.User;

public interface DML extends Tx {

    public void insert(Object obj);
    public void update(Object obj);
    public void delete(Object obj);
}
