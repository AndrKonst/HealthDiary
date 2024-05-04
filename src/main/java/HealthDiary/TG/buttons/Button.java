package HealthDiary.TG.buttons;

public enum Button {
    DIARY_LIST("Список дневников", "diary_list"),
    NEW_DIARY("Создать новый дневник", "new_diary"),
    DIARY_LIST_BACK("<< Назад", "back_btn"),
    DIARY_BACK("<< К списку дневников", "diary_back_btn");

    private final String text;
    private final String callbackText;

    Button(String text, String callbackText){
        this.text = text;
        this.callbackText = callbackText;
    }

    public String getText() {
        return text;
    }

    public String getCallbackText() {
        return callbackText;
    }
}
