package HealthDiary.TG;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

public class TGMessage {
    private SendMessage msg;

    private static final Logger logger = LoggerFactory.getLogger(
            TGMessage.class);

    public TGMessage(){
        this.msg = new SendMessage();

        logger.debug("init new TGMessage");
    }

    public void setMsgText(String msgText){
        // msg text
        this.msg.setText(msgText);

        logger.debug("setting text \"{}\"", msgText);
    }

    public void setChatId (@NotNull Long chat_id){
        //Who are we sending a message to
        this.msg.setChatId(chat_id.toString());
    }

    public void setKeyBoard(ReplyKeyboard kb){
        this.msg.setReplyMarkup(kb);

        logger.debug("set keyboard {}", kb.toString());
    }

    public void remove_keyboard(){
        this.msg.setReplyMarkup(new ReplyKeyboardRemove(true));

        logger.debug("remove keyboadrd");
    }

    public boolean isValid(){
        logger.debug("Chk valid Msg");
        return !msg.getChatId().isEmpty() && !msg.getText().isEmpty();
    }

    public SendMessage getMsg() {
        return msg;
    }
}
