package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.TextAnsw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiaryCreation extends Text{

    private static final Logger logger = LoggerFactory.getLogger(
            DiaryCreation.class);

    public DiaryCreation(String text, Integer step){
        super(text);
        this.setState(UserState.DIARY_CREATION);
        this.setStep(step);
        logger.debug("init DiaryCreation \"{}\"", text);
    }
}
