package by.epam.jwd.finalproj.command;

import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.ShowPeriodicalsCommand;
import by.epam.jwd.finalproj.command.user.LoginCommand;
import by.epam.jwd.finalproj.command.user.LogoutCommand;

public enum CommandManager {
    LOGIN(LoginCommand.INSTANCE),
    LOGOUT(LogoutCommand.INSTANCE),
    SHOWLOGIN(ShowLoginPageCommand.INSTANCE),
    DEFAULT(ShowPeriodicalsCommand.INSTANCE);

    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command retrieveCommand(String name){
        for(CommandManager command : values()){
            if (command.name().equalsIgnoreCase(name)){
                return command.command;
            }
        }
        return DEFAULT.command;
    }
}
