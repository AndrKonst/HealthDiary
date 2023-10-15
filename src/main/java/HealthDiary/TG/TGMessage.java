package HealthDiary.TG;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class TGMessage {
    private SendMessage msg;

    public TGMessage(){
        this.msg = new SendMessage();
    }

    public void setMsgText(String msgText){
        // msg text
        this.msg.setText(msgText);
    }

    public void setChatId (@NotNull Long chat_id){
        //Who are we sending a message to
        this.msg.setChatId(chat_id.toString());
    }

    public void setKeyBoard(InlineKeyboardMarkup kb){
        this.msg.setReplyMarkup(kb);
    }

    public boolean isValid(){
        if (msg.getChatId().isEmpty() || msg.getText().isEmpty()){
            return false;
        }
        return true;
    }

    public SendMessage getMsg() {
        return msg;
    }
}
