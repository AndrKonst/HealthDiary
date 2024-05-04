package HealthDiary.TG.Messages;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.DataBase.services.DiaryService;
import HealthDiary.TG.KeyboardAnsw;
import HealthDiary.TG.buttons.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class DiaryKb extends Text implements KeyboardAnsw {
    private ReplyKeyboard kb;
    private DbDiary diary;

    private static final Logger logger = LoggerFactory.getLogger(
            DiaryKb.class);

    public DiaryKb(String diaryName, DbUser user) {
        super(diaryName, user);

        this.diary = new DiaryService().findDiary(diaryName);
    }

    @Override
    public void prepareAnswer() {
        this.kb = setKeyboard();

        // set active diary
        setUserState(this.diary.getId());
    }

    @Override
    public ReplyKeyboard getKeyboard() {
        return this.kb;
    }

    private ReplyKeyboard setKeyboard() {

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow kbRow = new KeyboardRow();

        // start diary btn
        KeyboardButton startBtn = new KeyboardButton();
        startBtn.setText("Начать заполнение");
        kbRow.add(startBtn);

        // statistic btn
        KeyboardButton statBtn = new KeyboardButton();
        statBtn.setText("Получить статистику");
        kbRow.add(statBtn);

        rowList.add(kbRow);

        // back btn
        KeyboardRow backBtnRow = new KeyboardRow();
        KeyboardButton backBtn = new KeyboardButton();
        backBtn.setText(Button.DIARY_BACK.getText());
        backBtnRow.add(backBtn);
        rowList.add(backBtnRow);

        // Create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    private void setUserState(Integer state) {
        logger.debug("Set user state to \"{}\"", state);

        this.getUser().setState(state);
    }
}
