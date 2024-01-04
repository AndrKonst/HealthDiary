package HealthDiary.TG.Messages;

public enum UserState {
    EMPTY_STATE(null),
    START_MENU(-1),
    DIARY_CREATION(-2);

    private final Integer stateID;

    UserState(Integer stateID){
        this.stateID = stateID;
    }

    public Integer getStateID() {
        return stateID;
    }
}
