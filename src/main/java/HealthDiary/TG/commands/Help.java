package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;

public class Help implements TextAnsw {
    private String answText;
    @Override
    public void prepareAnswer(DbUser user) {
        answText = "help message";
    }

    @Override
    public String getAnswText() {
        return answText;
    }
}
