package HealthDiary.TG;

import HealthDiary.DataBase.models.DbUser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface KeyboardAnsw extends TextAnsw {

    public ReplyKeyboard getKeyboard();

    private ReplyKeyboard setKeyboard() {return null;}
}
