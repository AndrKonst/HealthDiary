package HealthDiary.TG;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import HealthDiary.TG.commands.*;
import HealthDiary.DataBase.services.*;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.exceptions.*;

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

        // check user
        DbUser user;
        UserService us = new UserService();

        try {
            user = us.findUser(userId);
        } catch (NoDataFound e) {
            user = new DbUser(userId, 0);
            us.insertUser(user);
        }

        // Getting text
        if (msg.isCommand()) {
            String command = msg.getText();

            CommandFactory cf = new CommandFactory(command);
            Answer answ =  cf.getCommand();
            sendText(user.getId(), answ.prepareAnswer());
        } else {
            if (msg.hasText()) {
                userText = msg.getText();

                // received text debug
                System.out.println("Usr "
                        + fromUser.getFirstName()
                        + " (Id "
                        + user.getId()
                        + ") wrote: "
                        + userText);

                // echo received text
                sendText(user.getId(), msg.getText());
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
