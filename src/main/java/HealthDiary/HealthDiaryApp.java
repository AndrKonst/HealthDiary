package HealthDiary;

import HealthDiary.TG.HealthDiaryTGBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthDiaryApp {

    private static final Logger logger = LoggerFactory.getLogger(
            HealthDiaryApp.class);

    public static void main(String[] args) {
        String botAppName = Param.APP_NAME.getVal();
        try {
            logger.info("Starting bot {} ...", botAppName);

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new HealthDiaryTGBot(botAppName, Param.APP_TOKEN.getVal()));

            logger.info("Bot {} started", botAppName);
        } catch (TelegramApiException e) {
            logger.error("Failed to start bot {}.", botAppName, e);
        }
    }
}
