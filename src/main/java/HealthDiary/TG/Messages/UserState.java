package HealthDiary.TG.Messages;

public enum UserState {
    KEYBOARD("keyboard"),
    DIARY_CREATION("diary_creation"),

    EMPTY_STATE(null);

    private final String stateID;

    UserState(String stateID){
        this.stateID = stateID;
    }

    public String getStateID() {
        return stateID;
    }
}
