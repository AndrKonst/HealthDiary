package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.services.DiaryService;
import HealthDiary.TG.TextAnsw;
import HealthDiary.TG.buttons.Button;
import HealthDiary.TG.commands.Start_kb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        // In case received user text return necessary answer class
        if (userText.equals(Button.NEW_DIARY.getText()) | user.getState() == UserState.DIARY_CREATION.getStateID()) {
            return new DiaryCreation( this.userText, user);
        } else if (userText.equals(Button.DIARY_LIST.getText())) {
            String res = "Список доступных дневников:\n";
            List<DbDiary> userDiaries = new DiaryService(user).getDiaryList();

            return new DiariesListKb(this.user, userDiaries);
        } else {
            if (user.getState() == UserState.BEFORE_NEW_QUESTION.getStateID()) {
                if (this.userText.equalsIgnoreCase("Да")) {
                    return new QuestionAdding(this.userText, this.user);
                } else if (this.userText.equalsIgnoreCase("Нет")) {
                    this.user.setState(UserState.EMPTY_STATE.getStateID());
                    this.user.setStep(0);

                    return new Text("Дневник создан!", this.user);
                } else {
                    return new Text("Ответ должен быть \"Да\" или \"Нет\" ", this.user);
                }
            } else if (user.getState() == UserState.BEFORE_NEW_ANSWER.getStateID()) {
                if (this.userText.equalsIgnoreCase("Да")) {
                    return new AnswerAdding(this.userText, this.user);
                } else if (this.userText.equalsIgnoreCase("Нет")) {
                    return new QuestionAdding(this.userText, this.user);
                } else {
                    return new Text("Ответ должен быть \"Да\" или \"Нет\" ", this.user);
                }
            } else if (user.getState() == UserState.ADDING_QUESTION_ANSWER.getStateID()) {
                return new AnswerAdding(this.userText, this.user);
            } else if (user.getState() == UserState.QUESTION_ADDING.getStateID()) {
                return new QuestionAdding(this.userText, this.user);
            } else if (userText.equals(Button.BACK.getText())) {
                return new Start_kb(this.user);
            } else {
                List<DbDiary> userDiaries = new DiaryService(user).getDiaryList();
                List<String> userDiariesNames = new ArrayList<>();

                for (DbDiary diary : userDiaries) {
                    userDiariesNames.add(diary.getName());
                }

                if (userDiariesNames.contains(userText)) {
                    logger.info("Starting diary \"{}\"", userText);
                    return null;
                } else {
                    logger.warn("Unknown text \"{}\"", userText);
                    return null;
                }
            }
        }
    }
}
