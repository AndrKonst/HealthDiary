package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.KeyboardAnsw;
import HealthDiary.TG.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class DiariesListKb extends Text implements KeyboardAnsw {
    private ReplyKeyboard kb;
    private List<DbDiary> diaryList;
    private static final int numberOfButtonsInRow = 3;

    private static final Logger logger = LoggerFactory.getLogger(
            DiariesListKb.class);

    public DiariesListKb(DbUser user, List<DbDiary> diaryList) {
        super("Доступные дневники:", user);

        this.diaryList = diaryList;
        logger.debug("setting keyboard for {} diaries", diaryList.size());
    }

    @Override
    public void prepareAnswer() {this.kb = setKeyboard();}

    @Override
    public ReplyKeyboard getKeyboard() {
        return this.kb;
    }

    private ReplyKeyboard setKeyboard() {
        int numberOfRows = (int) ceil((double) diaryList.size() / numberOfButtonsInRow);

        logger.debug("setting {} rows", numberOfRows + 1);

        List<KeyboardRow> rowList = getKeyboardRows(numberOfRows);

        // Create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    private @NotNull List<KeyboardRow> getKeyboardRows(int numberOfRows) {
        List<KeyboardRow> rowList = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++) {
            KeyboardRow btnRow = new KeyboardRow();

            for (int j = 0; j < (i == numberOfRows - 1 ? diaryList.size() % numberOfButtonsInRow : numberOfButtonsInRow); j++) {
                KeyboardButton button = new KeyboardButton();
                button.setText(diaryList.get(numberOfButtonsInRow * i + j).getName());
                btnRow.add(button);
            }

            rowList.add(btnRow);
        }

        // back btn
        KeyboardRow backBtnRow = new KeyboardRow();
        KeyboardButton backBtn = new KeyboardButton();
        backBtn.setText(Button.BACK.getText());
        backBtnRow.add(backBtn);
        rowList.add(backBtnRow);
        return rowList;
    }
}
