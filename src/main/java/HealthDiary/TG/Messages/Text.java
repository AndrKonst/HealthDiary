package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Text implements TextAnsw {

    private String botAnswText;
    private DbUser user;

    private static final Logger logger = LoggerFactory.getLogger(
            Text.class);

    public Text(String text, DbUser user){
        this.botAnswText = text;
        this.user = user;

        logger.debug("init Text \"{}\"", text);
    }

    public DbUser getUser() {
        return user;
    }

    public void setBotAnswText(String botAnswText) {
        this.botAnswText = botAnswText;
    }

    public void setUserState(UserState state) {
        logger.debug("Set user state to \"{}\"", state);

        this.user.setState(state.getStateID());
    }

    public void setUserStep(Integer step) {
        logger.debug("Set user step to \"{}\"", step);

        this.user.setStep(step);
    }

    @Override
    public void prepareAnswer() {}

    @Override
    public String getBotAnswText() {
        return botAnswText;
    }
}
