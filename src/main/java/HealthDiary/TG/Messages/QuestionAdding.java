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
        super(null, user);

        userAnswText = FromUserText;

    }

    @Override
    public void prepareAnswer() {
        super.prepareAnswer();

        DbDiaryFilling df = new DiaryFillingService().findDiaryFilling(this.getUser());
        // Find what diary is creating
        Integer diaryId = df.getCreationFl();

        if (diaryId == null){
            logger.debug("User {} are not creating any diary now", this.getUser());
            throw new NoDiary("User " + this.getUser() + "are not creating any diary now");
        } else {

            UserState state = UserState.EMPTY_STATE;

            // Set next answer to user
            if (this.getUser().getState() == UserState.QUESTION_ADDING.getStateID()){
                // Save question
                QuestionService qs = new QuestionService(diaryId);
                qs.addQuestion(this.userAnswText, this.getUser().getStep());

                this.setBotAnswText("Вопрос добавлен. Он имеет список ответов? (Да/Нет)");
                state = UserState.BEFORE_NEW_ANSWER;
            } else if (getUser().getState() == UserState.BEFORE_NEW_ANSWER.getStateID()) {
                int nextStep = QuestionService.getLastQuestPos(diaryId) + 1;

                this.setBotAnswText("Еще вопрос? (Да/Нет)");

                // Save step
                this.setUserStep(nextStep);

                state = UserState.BEFORE_NEW_QUESTION;
            } else if (getUser().getState() == UserState.BEFORE_NEW_QUESTION.getStateID()) {
                this.setBotAnswText("Введите текст вопроса");
                state = UserState.QUESTION_ADDING;
            }

            // Save State
            this.setUserState(state);
        }
    }
}
