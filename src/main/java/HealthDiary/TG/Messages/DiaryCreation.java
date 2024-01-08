package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.services.DiaryService;
import HealthDiary.TG.buttons.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiaryCreation extends Text {

    private String userAnswText;
    private static final Logger logger = LoggerFactory.getLogger(
            DiaryCreation.class);

    public DiaryCreation(String FromUserText, DbUser user){
        super(null, user);

        // Так как это 1 шаг, то статус и шаг тут могут быть null
        if (user.getStep() == null | user.getStep() == 0){
            this.setBotAnswText("Как назовем дневник?");
            this.setUserStep(1);
            this.setUserState(UserState.DIARY_CREATION);
        } else {
            // текст след. вопроса
            this.setBotAnswText("Какой 1 вопрос?");
        }

        this.userAnswText = FromUserText;

        logger.debug("init DiaryCreation");
    }

    @Override
    public void prepareAnswer() {
        super.prepareAnswer();

        if (!this.userAnswText.equals(Button.NEW_DIARY.getText())){
            try {
                // Создаем Дневник
                DiaryService ds = new DiaryService(this.getUser());
                ds.createDiary(this.userAnswText);

                // Состояние и шаг
                this.setUserState(UserState.QUESTION_ADDING);
                this.setUserStep(1);
            } catch (Exception e) {
                // Дневник не получилось создать
                this.setBotAnswText("Не получилось создать дневник с таким именем \uD83E\uDD37\u200D♂\uFE0F\nКакое новое имя?");

                // Меняем шаг для повтора ввода названия дневника
                this.setUserStep(2);
            }
        }
    }
}
