package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.KeyboardAnsw;
import HealthDiary.TG.Messages.Text;
import HealthDiary.TG.Messages.UserState;
import HealthDiary.TG.buttons.Button;
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
        super(null);
        if (user.getIsAdmin() == 1){
            setAnswText("Посмотри список твоих дневников или создай новый");
        } else {
            setAnswText("Посмотри список твоих дневников");
        }

        setState(UserState.START_MENU);
    }

    @Override
    public void prepareAnswer(DbUser user) {
        logger.debug("Prepare Start answer");

        this.kb = setKeyboard(user);
    }

    public ReplyKeyboard getKeyboard(){
        return this.kb;
    }

    public ReplyKeyboard setKeyboard(DbUser user) {
        // Ряды
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow keyboardButtonsRow1 = new KeyboardRow();
        // Список дневников
        KeyboardButton button1_1 = new KeyboardButton();
        button1_1.setText(Button.DIARY_LIST.getText());
        keyboardButtonsRow1.add(button1_1);

        // Новый дневник
        if (user.getIsAdmin() == 1){
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
