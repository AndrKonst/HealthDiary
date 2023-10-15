package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.KeyboardAnsw;
import HealthDiary.TG.buttons.Button;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Start implements KeyboardAnsw {

    private String answText;
    private InlineKeyboardMarkup kb;

    @Override
    public void prepareAnswer(DbUser user) {
        this.answText = "Start button msg";
        this.kb = setKeyboard(user);
    }

    @Override
    public String getAnswText() {
        return answText;
    }

    public InlineKeyboardMarkup getKeyboard(){
        return this.kb;
    }

    public InlineKeyboardMarkup setKeyboard(DbUser user) {
        // Ряды
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        // Список дневников
        InlineKeyboardButton button1_1 = new InlineKeyboardButton();
        button1_1.setText(Button.DIARY_LIST.getText());
        button1_1.setCallbackData(Button.DIARY_LIST.getCallbackText());
        keyboardButtonsRow1.add(button1_1);

        // Новый дневник
        if (user.getIsAdmin() == 1){
            InlineKeyboardButton button1_2 = new InlineKeyboardButton();
            button1_2.setText(Button.NEW_DIARY.getText());
            button1_2.setCallbackData(Button.NEW_DIARY.getCallbackText());
            keyboardButtonsRow1.add(button1_2);
        }

        // Добавляем ряды кнопок в список
        rowList.add(keyboardButtonsRow1);

        //Создаем клавиатуру
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rowList);

        return keyboardMarkup;
    }

}
