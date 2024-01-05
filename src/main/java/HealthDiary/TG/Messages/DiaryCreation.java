package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.services.DiaryFillingService;
import HealthDiary.DataBase.services.DiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiaryCreation extends Text {

    private String userAnswText;
    private static final Logger logger = LoggerFactory.getLogger(
            DiaryCreation.class);

    public DiaryCreation(String botAnswText, String Answ2UserText, Integer curStep){
        super(botAnswText);
        this.setState(UserState.DIARY_CREATION);

        if (curStep == null){
            curStep = 0;
        }

        this.setReqStep(curStep + 1);
        this.setCurStep(curStep);

        this.userAnswText = Answ2UserText;

        logger.debug("init DiaryCreation \"{}\"", botAnswText);
    }

    @Override
    public void prepareAnswer(DbUser user) {
        super.prepareAnswer(user);

        if (this.getCurStep() == 1){ // Ввели название дневника, создаем его
            try {
                // Создаем Дневник
                DiaryService ds = new DiaryService(user);
                ds.createDiary(this.userAnswText);
            } catch (Exception e) {
                // Дневник не получилось создать
                this.setAnswText("Не получилось создать дневник с таким именем \uD83E\uDD37\u200D♂\uFE0F\nКакое новое имя?");
                this.setReqStep(this.getCurStep());

                // Меняем шаг у пользователя, для повторного ввода названия дневника
                logger.debug("Change user step");
                user.setStep(this.getRequiredUserStep());
            }
        }
    }
}
