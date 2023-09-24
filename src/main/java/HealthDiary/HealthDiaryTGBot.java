package HealthDiary;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HealthDiaryTGBot extends TelegramLongPollingBot {

    private final String BOTNAME;
    private final String BOTTOKEN;

    public HealthDiaryTGBot(String name, String token) {
        this.BOTNAME = name;
        this.BOTTOKEN = token;
    }

    @Override
    public String getBotUsername() {
        return this.BOTNAME;
    }

    @Override
    public String getBotToken() {
        return this.BOTTOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        // receive update params
        Message msg = update.getMessage();
        User fromUser = msg.getFrom();
        Long userId = fromUser.getId();
        String userText = null;

        // Getting text
        if (msg.isCommand()) {
            System.out.println("command: " + msg.getText());

        } else {
            if (msg.hasText()) {
                userText = msg.getText();

                // received text debug
                System.out.println("Usr "
                        + fromUser.getFirstName()
                        + " (Id "
                        + userId
                        + ") wrote: "
                        + userText);

                // echo received text
                sendText(userId, msg.getText());
            }
        }
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
