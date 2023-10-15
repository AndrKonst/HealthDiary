package HealthDiary.TG;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class TGMessage {
    private SendMessage msg;

    TGMessage(){
        this.msg = new SendMessage();
    }

    void setMsgText(String msgText){
        // msg text
        this.msg.setText(msgText);
    }

    void setChatId (@NotNull Long chat_id){
        //Who are we sending a message to
        this.msg.setChatId(chat_id.toString());
    }

    void setKeyBoard(InlineKeyboardMarkup kb){
        this.msg.setReplyMarkup(kb);
    }

    public SendMessage getMsg() {
        return msg;
    }
}
