package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.Answer;
import HealthDiary.TG.buttons.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DialogFactory {
    private String userText;
    private DbUser user;

    private static final Logger logger = LoggerFactory.getLogger(
            DialogFactory.class);

    public DialogFactory(DbUser user, String userText){
        this.user = user;
        this.userText = userText;

        logger.debug("Usr (Id {}) wrote: {}", user.getId(), userText);
    }

    public Answer getAnswer(){
        if (userText.equals(Button.NEW_DIARY.getText())) {
            return new DiaryCreation("Как назовем дневник?", null, user.getStep());
        } else if (userText.equals(Button.DIARY_LIST.getText())) {
            return null;
        } else {
            if (user.getState() == UserState.DIARY_CREATION.getStateID()){ // Diary_creation
                return new DiaryCreation("Какой " + user.getStep() + " вопрос?", userText, user.getStep());
            } else {
                logger.warn("Unknown text \"{}\"", userText);
                return null;
            }
        }
    }
}
