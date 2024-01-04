package HealthDiary.TG.Messages;

public enum UserState {
    START_MENU(-2),
    DIARY_CREATION(-1),
    EMPTY_STATE(0);

    private final int stateID;

    UserState(int stateID){
        this.stateID = stateID;
    }

    public int getStateID() {
        return stateID;
    }
}
