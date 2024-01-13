package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.HealthDiaryTGBot;
import HealthDiary.TG.Messages.Text;
import HealthDiary.TG.Messages.UserState;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Help extends Text {

    public Help(DbUser user) {
        super("help message", user);
    }

    @Override
    public void prepareAnswer() {
        super.prepareAnswer();

        if (this.getUser().getState() < 0){
            setBotAnswText(
                    "Current info:\n" +
                    UserState.findState(this.getUser().getState()).toString() + "\n" +
                    "step: " + this.getUser().getStep().toString());
        }
    }
}
