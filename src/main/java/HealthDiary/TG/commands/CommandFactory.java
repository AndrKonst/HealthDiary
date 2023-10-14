package HealthDiary.TG.commands;

public class CommandFactory {

    private String command;

    public CommandFactory(String command){
        this.command = command;
    }

    public Answer getCommand(){
        if (command.startsWith("/help")) {
            return new Help();
        } else if (command.startsWith("/start")) {
            return new Start();
        } else {
            System.out.println("command: " + command);
            return null;
        }
    }
}
