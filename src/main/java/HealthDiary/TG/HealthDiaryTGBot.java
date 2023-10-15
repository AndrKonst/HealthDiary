package HealthDiary.TG;

import HealthDiary.TG.buttons.BtnCallbackFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import HealthDiary.TG.commands.*;
import HealthDiary.DataBase.services.*;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.exceptions.*;
import HealthDiary.TG.Messages.Text;

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
        TGMessage sendMsg = new TGMessage();
        UserService us = new UserService();
        DbUser user;

        if (update.hasMessage()) {
            Message msg = update.getMessage();
            User fromUser = msg.getFrom();
            Long userId = fromUser.getId();

            // check user
            try {
                user = us.findUser(userId);
            } catch (NoDataFound e) {
                user = new DbUser(userId, 0);
                us.insertUser(user);
            }

            // Getting text
            if (msg.isCommand()) {

                CommandFactory cf = new CommandFactory(msg.getText());
                Answer answ = cf.getCommand();

                sendMsg = initMsg(answ, user, sendMsg);
            } else {
                if (msg.hasText()) {
                    String userText = msg.getText();

                    // received text debug
                    System.out.println("Usr "
                            + fromUser.getFirstName()
                            + " (Id "
                            + user.getId()
                            + ") wrote: "
                            + userText);

                    // echo received text
                    sendMsg = initMsg(new Text(msg.getText()), user, sendMsg);
                }
            }
        }

        // Обработка нажатия на inline кнопку
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String buttonData = callbackQuery.getData();

            // Так как это колбэк, то пользователь точно есть
            user = us.findUser(callbackQuery.getFrom().getId());

            // генерим ответ
            BtnCallbackFactory bcf = new BtnCallbackFactory(buttonData);
            Answer answ = bcf.getBtnCallback();
            sendMsg = initMsg(answ, user, sendMsg);
        }

        if (sendMsg.isValid()){
            sendMsg2Tg(sendMsg.getMsg());
        } else {
            System.out.println("Not valid msg");
        }
    }

    private void sendMsg2Tg(SendMessage sm){
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    private TGMessage initMsg(Answer answ, DbUser user, TGMessage sendMsg){
        answ.prepareAnswer(user);

        // Кому отправляем
        sendMsg.setChatId(user.getId());

        // Текст сообщения
        if (answ instanceof TextAnsw) {
            sendMsg.setMsgText(((TextAnsw) answ).getAnswText());
        }

        // Клавиатура
        if (answ instanceof KeyboardAnsw) {
            sendMsg.setKeyBoard(((KeyboardAnsw) answ).getKeyboard());
        }

        return sendMsg;
    }
}
