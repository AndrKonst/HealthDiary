package HealthDiary;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class HealthDiaryApp {
    private static final Map<String, String> env = System.getenv();

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new HealthDiaryTGBot(env.get("MOODDIARYNAME"), env.get("MOODDIARYTOKEN")));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
