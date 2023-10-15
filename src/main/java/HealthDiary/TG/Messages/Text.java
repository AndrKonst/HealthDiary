package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;

public class Text implements TextAnsw {

    private String answText;


    public Text(String text){
        this.answText = text;
    }
    @Override
    public void prepareAnswer(DbUser user) {

    }

    @Override
    public String getAnswText() {
        return answText;
    }
}
