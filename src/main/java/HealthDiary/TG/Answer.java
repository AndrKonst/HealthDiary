package HealthDiary.TG;

import HealthDiary.DataBase.models.DbUser;

public interface Answer {

    void prepareAnswer(DbUser user);

    int getRequiredUserState();

}
