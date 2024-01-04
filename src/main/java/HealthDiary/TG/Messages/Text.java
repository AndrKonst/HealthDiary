package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Text implements TextAnsw {

    private String answText;
    public final UserState state = UserState.EMPTY_STATE;

    private static final Logger logger = LoggerFactory.getLogger(
            Text.class);

    public Text(String text){
        this.answText = text;
        logger.debug("init Text \"{}\"", text);
    }
    @Override
    public void prepareAnswer(DbUser user) {
    }

    @Override
    public String getAnswText() {
        return answText;
    }

    @Override
    public int getRequiredUserState(){
        return state.getStateID();
    };
}
