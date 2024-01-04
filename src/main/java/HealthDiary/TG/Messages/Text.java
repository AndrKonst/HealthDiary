package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Text implements TextAnsw {

    private String answText;
    private UserState state = UserState.EMPTY_STATE;
    private Integer step = null;

    private static final Logger logger = LoggerFactory.getLogger(
            Text.class);

    public Text(String text){
        this.answText = text;
        logger.debug("init Text \"{}\"", text);
    }

    public void setAnswText(String answText) {
        this.answText = answText;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public void prepareAnswer(DbUser user) {
    }

    @Override
    public String getAnswText() {
        return answText;
    }

    @Override
    public Integer getRequiredUserState(){
        return state.getStateID();
    };

    @Override
    public Integer getRequiredUserStep(){
        return step;
    };
}
