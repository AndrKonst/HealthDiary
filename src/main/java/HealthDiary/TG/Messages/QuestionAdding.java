package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.*;
import HealthDiary.DataBase.services.*;
import HealthDiary.exceptions.NoDiary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionAdding extends Text {

    private String userAnswText;
    private static final Logger logger = LoggerFactory.getLogger(
            QuestionAdding.class);

    public QuestionAdding(String FromUserText, DbUser user){
        super("Вопрос имеет список ответов?", user);

        userAnswText = FromUserText;

    }

    @Override
    public void prepareAnswer() {
        super.prepareAnswer();

        DbDiaryFilling df = new DiaryFillingService().findDiaryFilling(this.getUser());
           Integer diaryId = df.getCreationFl();
           if (diaryId == null){
               logger.debug("User {} are not creating any diary now", this.getUser());
               throw new NoDiary("User " + this.getUser() + "are not creating any diary now");
           } else {
               // Сохраняем вопрос
               QuestionService qs = new QuestionService(diaryId);
               qs.addQuestion(this.userAnswText, this.getUser().getStep());

               // Состояние и шаг
               this.setUserState(UserState.ADDING_QUESTION_ANSWERS);
               this.setUserStep(1);
           }
    }
}
