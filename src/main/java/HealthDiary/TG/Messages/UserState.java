package HealthDiary.TG.Messages;

public enum UserState {
    EMPTY_STATE(null, "No active diaries"),
    START_MENU(-1, "Start menu"),
    DIARY_CREATION(-2, "Diary Creation"),
    QUESTION_ADDING(-3, "Question adding"),
    ADDING_QUESTION_ANSWER(-4, "Adding question answer"),
    BEFORE_NEW_QUESTION(-5, "Asking if need another question"),
    BEFORE_NEW_ANSWER(-6, "Asking if need another answer");

    private final Integer stateID;
    private final String stateName;

    UserState(Integer stateID, String stateName){
        this.stateID = stateID;
        this.stateName = stateName;
    }

    public Integer getStateID() {
        return stateID;
    }

    @Override
    public String toString() {
        return "state: " + this.stateName;
    }

    public static UserState findState(Integer stateId){
        UserState res;

        switch (stateId) {
            case -1: {
                res = UserState.START_MENU;
                break;
            }
            case -2: {
                res = UserState.DIARY_CREATION;
                break;
            }
            case -3: {
                res = UserState.QUESTION_ADDING;
                break;
            }
            case -4: {
                res = UserState.ADDING_QUESTION_ANSWER;
                break;
            }
            default: {
                res = UserState.EMPTY_STATE;
                break;
            }
        }

        return res;
    }
}
