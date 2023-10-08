package HealthDiary;

import HealthDiary.TG.HealthDiaryTGBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class HealthDiaryApp {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new HealthDiaryTGBot(Param.APP_NAME.getVal(), Param.APP_TOKEN.getVal()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
