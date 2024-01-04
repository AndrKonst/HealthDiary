package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiaryCreation implements TextAnsw {
    private String answText;
    public final UserState state = UserState.DIARY_CREATION;

    private static final Logger logger = LoggerFactory.getLogger(
            DiaryCreation.class);

    public DiaryCreation(String text){
        this.answText = text;
        logger.debug("init DiaryCreation \"{}\"", text);
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
