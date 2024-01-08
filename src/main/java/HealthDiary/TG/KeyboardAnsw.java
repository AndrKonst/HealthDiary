package HealthDiary.TG;

import HealthDiary.DataBase.models.DbUser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface KeyboardAnsw extends TextAnsw {

    ReplyKeyboard getKeyboard();

    ReplyKeyboard setKeyboard();
}
