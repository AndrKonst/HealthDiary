package HealthDiary.TG;

import HealthDiary.TG.buttons.BtnCallbackFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import HealthDiary.TG.commands.*;
import HealthDiary.DataBase.services.*;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.exceptions.*;
import HealthDiary.TG.Messages.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class HealthDiaryTGBot extends TelegramLongPollingBot {

    private final String BOTNAME;
    private final String BOTTOKEN;

    private static final Logger logger = LoggerFactory.getLogger(
            HealthDiaryTGBot.class);

    public HealthDiaryTGBot(String name, String token) {
        this.BOTNAME = name;
        this.BOTTOKEN = token;

        logger.debug("Initiated bot {}", name);
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

        logger.debug("Received Update: {}", update.toString());

        // receive update params
        TGMessage sendMsg = new TGMessage();
        UserService us = new UserService();
        DbUser user = null;
        String user_state = null;

        if (update.hasMessage()) {
            logger.debug("Processing msg...");

            Message msg = update.getMessage();
            User fromUser = msg.getFrom();
            Long userId = fromUser.getId();

            // check user
            logger.debug("Check user {}", userId);
            try {
                user = us.findUser(userId);
            } catch (NoDataFound e) {
                user = new DbUser(userId, 0, null);
                us.insertUser(user);
            }
            user_state = user.getState();

            // Getting text
            if (msg.isCommand()) {

                CommandFactory cf = new CommandFactory(msg.getText());
                Answer answ = cf.getCommand();

                sendMsg = initMsg(answ, user, sendMsg);
            } else {
                if (msg.hasText()) {

                    DialogFactory df = new DialogFactory(user, msg.getText());
                    Answer answ = df.getAnswer();

                    sendMsg = initMsg(answ, user, sendMsg);
                }
            }
            logger.debug("Msg processed");
        }

        // Обработка нажатия на inline кнопку
        if (update.hasCallbackQuery()) {
            logger.debug("Processing inline btn pressed");

            CallbackQuery callbackQuery = update.getCallbackQuery();
            String buttonData = callbackQuery.getData();

            // Так как это колбэк, то пользователь точно есть
            user = us.findUser(callbackQuery.getFrom().getId());
            user_state = user.getState();

            // генерим ответ
            BtnCallbackFactory bcf = new BtnCallbackFactory(buttonData);
            Answer answ = bcf.getBtnCallback();
            sendMsg = initMsg(answ, user, sendMsg);

            logger.debug("Inline btn processed");
        }

        // Действия связанные с состоянием пользователя
        if (user_state != null){
            if ((user_state.equals(UserState.KEYBOARD.getStateID())) & (user.getState() == null)){
                logger.debug("Remove keyboard");
                sendMsg.remove_keyboard();
            }
        }

        // Сохраним пользователя, так как могли поменять его состояние
        logger.debug("before update state");
        us.updateUser(user);

        if (sendMsg.isValid()){
            sendMsg2Tg(sendMsg.getMsg());
        } else {
            logger.error("Not valid msg");
        }
    }

    private void sendMsg2Tg(SendMessage sm){
        logger.debug("Sending msg {}", sm.toString());
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            logger.error("Not valid msg", e);
            throw new RuntimeException(e);      //Any error will be printed here
        }

        logger.debug("Msg sent");
    }

    private TGMessage initMsg(Answer answ, DbUser user, TGMessage sendMsg){
        logger.debug("Start msg initiation");

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

        // Состояние
        if (answ instanceof KeyboardAnsw){
            logger.debug("Change user state to \"keyboard\"");
            user.setState(UserState.KEYBOARD.getStateID());
        } else if (answ instanceof DiaryCreation) {
            logger.debug("Change user state to Diari");
            user.setState(UserState.DIARY_CREATION.getStateID());
        } else {
            // Выставляем в статус пользователя, чтобы потом убрать клавиатуру
            logger.debug("Change user state to null");
            user.setState(UserState.EMPTY_STATE.getStateID());
        }

        logger.debug("Msg initiated");

        return sendMsg;
    }
}
