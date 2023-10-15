package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;
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
        // Создаем кнопки
        InlineKeyboardButton button1_1 = new InlineKeyboardButton();
        button1_1.setText("Нажми меня");
        button1_1.setCallbackData("button_clicked"); // Данные, которые будут отправлены обратно при нажатии на кнопку

        // Создаем списки кнопок (ряды) и добавляем в них кнопки
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button1_1);

        // Добавляем ряды кнопок в список
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        //Создаем клавиатуру
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rowList);

        return keyboardMarkup;
    }

}
