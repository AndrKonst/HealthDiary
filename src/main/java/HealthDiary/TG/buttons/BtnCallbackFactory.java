package HealthDiary.TG.buttons;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.Answer;
import HealthDiary.TG.commands.CommandFactory;
import HealthDiary.TG.Messages.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BtnCallbackFactory {

    private Button btn;

    private static final Logger logger = LoggerFactory.getLogger(
            BtnCallbackFactory.class);

    public BtnCallbackFactory(String callbackData){
        logger.debug("Call BtnCallbackFactory");

        logger.debug("callbackData: " + callbackData.toString());

        if (callbackData.equals(Button.NEW_DIARY.getCallbackText())) {
            this.btn = Button.NEW_DIARY;
            } else if (callbackData.equals(Button.DIARY_LIST.getCallbackText())) {
            this.btn = Button.DIARY_LIST;
        }
    };

    public Answer getBtnCallback(DbUser user){

        logger.debug("Getting callback for button \"{}\" ({})", btn.getText(), btn.getCallbackText());

        switch (btn) {
            case DIARY_LIST:
                return null;
            case NEW_DIARY:
                return new Text("Создание нового дневника", user);
        }
        return null;
    }
}
