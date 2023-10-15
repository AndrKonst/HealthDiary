package HealthDiary.TG;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface KeyboardAnsw extends TextAnsw {

    InlineKeyboardMarkup getKeyboard();
}
