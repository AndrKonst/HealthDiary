package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.HealthDiaryTGBot;
import HealthDiary.TG.Messages.UserState;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Help implements TextAnsw {
    private String answText;

    public final UserState state = UserState.EMPTY_STATE;

    private static final Logger logger = LoggerFactory.getLogger(
            Help.class);

    @Override
    public void prepareAnswer(DbUser user) {
        logger.debug("Prepare Help answer");

        answText = "help message";
    }

    @Override
    public String getAnswText() {
        logger.debug("return Help text");

        return answText;
    }

    @Override
    public int getRequiredUserState(){
        return state.getStateID();
    };
}
