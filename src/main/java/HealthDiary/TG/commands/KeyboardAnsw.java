package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface KeyboardAnsw extends TextAnsw {

    InlineKeyboardMarkup getKeyboard();
}
