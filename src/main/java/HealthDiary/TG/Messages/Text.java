package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Text implements TextAnsw {

    private String answText;
    private UserState state = UserState.EMPTY_STATE;
    private Integer reqStep = null;
    private Integer curStep = null;

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

    public void setReqStep(Integer reqStep) {
        this.reqStep = reqStep;
    }

    public Integer getCurStep() {
        return curStep;
    }

    public void setCurStep(Integer curStep) {
        this.curStep = curStep;
    }

    @Override
    public void prepareAnswer(DbUser user) {
        // Сохраняем данные состояния пользователя
        // Состояние
        logger.debug("Change user state");
        user.setState(this.getRequiredUserState());
        // Шаг
        logger.debug("Change user step");
        user.setStep(this.getRequiredUserStep());
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
        return reqStep;
    };
}
