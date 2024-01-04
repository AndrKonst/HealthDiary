package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.services.DiaryService;
import HealthDiary.TG.Answer;
import HealthDiary.TG.buttons.Button;
import HealthDiary.TG.commands.Help;
import HealthDiary.TG.commands.Start_kb;
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
            return new DiaryCreation("Как назовем дневник?", 1);
        } else if (userText.equals(Button.DIARY_LIST.getText())) {
            return null;
        } else {
            Integer state = user.getState();
            Integer step = user.getStep();

            if (state == UserState.DIARY_CREATION.getStateID()){ // Diary_creation
                if (step == 1){ // Ввели название дневника, создаем его
                    DiaryService ds = new DiaryService();
                    ds.createDiary(userText);

                    return new DiaryCreation("Какой 1 вопрос?", 2);
                }
            } else {
                logger.warn("Unknown text \"{}\"", userText);
                return null;
            }
        }
        return null;
    }
}
