package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbDiaryFilling;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.services.DiaryFillingService;
import HealthDiary.DataBase.services.DiaryService;
import HealthDiary.TG.KeyboardAnsw;
import HealthDiary.TG.Messages.Text;
import HealthDiary.TG.Messages.UserState;
import HealthDiary.TG.buttons.Button;
import HealthDiary.exceptions.NoDataFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Start_kb extends Text implements KeyboardAnsw {
    private ReplyKeyboard kb;

    private static final Logger logger = LoggerFactory.getLogger(
            Start_kb.class);

    public Start_kb(DbUser user) {
        super(null, user);
        if (user.getIsAdmin() == 1){
            this.setBotAnswText("Посмотри список твоих дневников или создай новый");
        } else {
            this.setBotAnswText("Посмотри список твоих дневников");
        }
    }

    @Override
    public void prepareAnswer() {
        super.prepareAnswer();
        logger.debug("Prepare Start answer");

        Integer stateId = this.getUser().getState();

        if (stateId != null){
            if (stateId < UserState.START_MENU.getStateID()){
                try {
                    // Find what diary is creating now
                    DbDiaryFilling df = new DiaryFillingService().findDiaryFilling(this.getUser());
                    Integer diaryId = df.getCreationFl();

                    //close unfinished diary
                    logger.debug("close diary {}", diaryId);
                    DiaryService ds = new DiaryService(this.getUser());
                    ds.closeDiary(diaryId);
                } catch (NoDataFound ignored){}

                // set default state
                logger.debug("default state and step");
                this.setUserState(UserState.START_MENU);
                this.setUserStep(0);
            }
        }

        this.kb = setKeyboard();
    }

    public ReplyKeyboard getKeyboard(){
        return this.kb;
    }

    private ReplyKeyboard setKeyboard() {
        // Ряды
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow keyboardButtonsRow1 = new KeyboardRow();
        // Список дневников
        KeyboardButton button1_1 = new KeyboardButton();
        button1_1.setText(Button.DIARY_LIST.getText());
        keyboardButtonsRow1.add(button1_1);

        // Новый дневник
        if (this.getUser().getIsAdmin() == 1){
            KeyboardButton button1_2 = new KeyboardButton();
            button1_2.setText(Button.NEW_DIARY.getText());
            keyboardButtonsRow1.add(button1_2);
        }

        // Добавляем ряды кнопок в список
        rowList.add(keyboardButtonsRow1);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        // Добавляем кнопки
        replyKeyboardMarkup.setKeyboard(rowList);
        // меняем размер
        replyKeyboardMarkup.setResizeKeyboard(true);
        // Выставляем флаг скрыть после нажатия
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
}
