package HealthDiary;

import java.util.Map;

public enum Param {
    APP_NAME ("BOT_NAME"),
    APP_TOKEN ("BOT_TOKEN"),
    DB_HOST ("DB_HOST"),
    DB_PORT ("DB_PORT"),
    DB_NAME ("DB_NAME"),
    DB_USER ("DB_USER"),
    DB_PASS ("DB_PASS");

    private String val;

    Param(String param_name){

        this.val = System.getenv(param_name);
    }

    public String getVal() {
        return this.val;
    }
}
