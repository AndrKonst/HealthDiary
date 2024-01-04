package HealthDiary.TG.commands;

import HealthDiary.DataBase.models.DbUser;
import HealthDiary.TG.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandFactory {

    private String command;
    private DbUser user;

    private static final Logger logger = LoggerFactory.getLogger(
            CommandFactory.class);

    public CommandFactory(String command, DbUser user){
        this.command = command;
        this.user = user;

        logger.debug("Get command \"{}\"", command);
    }

    public Answer getCommand(){
        if (command.startsWith("/help")) {
            return new Help();
        } else if (command.startsWith("/start")) {
            return new Start_kb(user);
        } else {
            logger.warn("Unknown command \"{}\"", command);
            return null;
        }
    }
}
