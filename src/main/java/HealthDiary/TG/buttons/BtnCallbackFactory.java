package HealthDiary.TG.buttons;

import HealthDiary.TG.Answer;

public class BtnCallbackFactory {

    private Button btn;

    public BtnCallbackFactory(String callbackData){
        if (callbackData.equals(Button.NEW_DIARY.getCallbackText())) {
            this.btn = Button.NEW_DIARY;
            } else if (callbackData.equals(Button.DIARY_LIST.getCallbackText())) {
            this.btn = Button.DIARY_LIST;
        }
    };

    public Answer getBtnCallback(){
        switch (btn) {
            case DIARY_LIST:
                return null;
            case NEW_DIARY:
                return null;
        }
        return null;
    }
}
