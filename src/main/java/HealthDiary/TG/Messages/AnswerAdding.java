package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbDiaryFilling;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.services.AnswerService;
import HealthDiary.DataBase.services.DiaryFillingService;
import HealthDiary.DataBase.services.QuestionService;
import HealthDiary.exceptions.NoDiary;
import HealthDiary.exceptions.NotNeedAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnswerAdding extends Text {

    private String userAnswText;
    private static final Logger logger = LoggerFactory.getLogger(
            AnswerAdding.class);

    public AnswerAdding(String FromUserText, DbUser user){
        super("Вопрос имеет список ответов?", user);

        userAnswText = FromUserText;
    }

    @Override
    public void prepareAnswer() {
        super.prepareAnswer();

        DbDiaryFilling df = new DiaryFillingService().findDiaryFilling(this.getUser());
        // Find what diary is creating
        Integer diaryId = df.getCreationFl();

        UserState state = UserState.EMPTY_STATE;

        if (diaryId == null){
            logger.debug("User {} are not creating any diary now", this.getUser());
            throw new NoDiary("User " + this.getUser() + "are not creating any diary now");
        } else {
            // Set next answer to user
            if (this.getUser().getState() == UserState.BEFORE_NEW_ANSWER.getStateID()){

                this.setBotAnswText("Введи текст " + "варианта ответа");
                state = UserState.ADDING_QUESTION_ANSWER;
            } else if (this.getUser().getState() == UserState.ADDING_QUESTION_ANSWER.getStateID()) {
                // Save answer
                AnswerService aServ = new AnswerService(diaryId, this.getUser().getStep());

                // step is current question position for diary
                int questionId = new QuestionService(diaryId).findQuestionByPos(this.getUser().getStep()).getQuestionId();
                aServ.addAnswer(this.userAnswText, (AnswerService.getLastAnswPos(questionId) + 1));

                this.setBotAnswText("Вариант ответа добавлен. Еще один? (Да/Нет)");
                state = UserState.BEFORE_NEW_ANSWER;
            }
        }

        // Save State
        this.setUserState(state);
    }
}
