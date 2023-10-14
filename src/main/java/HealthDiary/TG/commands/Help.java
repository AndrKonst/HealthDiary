package HealthDiary.TG.commands;

import java.util.List;

public class Help implements Answer {
    @Override
    public String prepareAnswer() {
        return "help message";
    }
}
