package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;
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

    public TextAnsw getAnswer(){
        if (userText.equals(Button.NEW_DIARY.getText()) | user.getState() == UserState.DIARY_CREATION.getStateID()) {
            return new DiaryCreation( this.userText, user);
        } else if (userText.equals(Button.DIARY_LIST.getText())) {
            return null;
        } else {
            if (user.getState() == UserState.QUESTION_ADDING.getStateID()) {
                return new QuestionAdding(this.userText, this.user);
            } else {
                logger.warn("Unknown text \"{}\"", userText);
                return null;
            }
        }
    }
}
