package HealthDiary.TG.buttons;

import HealthDiary.TG.Answer;
import HealthDiary.TG.commands.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BtnCallbackFactory {

    private Button btn;

    private static final Logger logger = LoggerFactory.getLogger(
            BtnCallbackFactory.class);

    public BtnCallbackFactory(String callbackData){
        logger.debug("Call BtnCallbackFactory");

        if (callbackData.equals(Button.NEW_DIARY.getCallbackText())) {
            this.btn = Button.NEW_DIARY;
            } else if (callbackData.equals(Button.DIARY_LIST.getCallbackText())) {
            this.btn = Button.DIARY_LIST;
        }
    };

    public Answer getBtnCallback(){

        logger.debug("Getting callback for button \"{}\"", btn.getText());

        switch (btn) {
            case DIARY_LIST:
                return null;
            case NEW_DIARY:
                return null;
        }
        return null;
    }
}
