package HealthDiary.TG;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface KeyboardAnsw extends TextAnsw {

    ReplyKeyboard getKeyboard();
}
