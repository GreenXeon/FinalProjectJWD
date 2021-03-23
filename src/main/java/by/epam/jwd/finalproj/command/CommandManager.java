package by.epam.jwd.finalproj.command;

import by.epam.jwd.finalproj.command.action.admin.AddPeriodicalCommand;
import by.epam.jwd.finalproj.command.action.admin.DeletePeriodicalCommand;
import by.epam.jwd.finalproj.command.action.admin.UpdatePeriodicalCommand;
import by.epam.jwd.finalproj.command.page.user.ShowSubscribeCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowUpdatePeriodicalCommand;
import by.epam.jwd.finalproj.command.page.*;
import by.epam.jwd.finalproj.command.page.admin.ShowAddPeriodicalCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowAllUsersCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.action.LoginCommand;
import by.epam.jwd.finalproj.command.action.LogoutCommand;
import by.epam.jwd.finalproj.command.action.SignUpCommand;

public enum CommandManager {
    LOGIN(LoginCommand.INSTANCE),
    LOGOUT(LogoutCommand.INSTANCE),
    SIGNUP(SignUpCommand.INSTANCE),

    SHOWLOGIN(ShowLoginPageCommand.INSTANCE),
    SHOWSIGNUP(ShowSignUpPageCommand.INSTANCE),
    SHOWGUEST(ShowGuestPageCommand.INSTANCE),
    SHOW_ADD_PER(ShowAddPeriodicalCommand.INSTANCE),
    SHOW_ERROR(ShowErrorPageCommand.INSTANCE),
    SHOW_USER_MAIN(ShowMainPageCommand.INSTANCE),
    SHOW_PER_ADMIN(ShowMainAdminPageCommand.INSTANCE),
    SHOW_ALL_USERS(ShowAllUsersCommand.INSTANCE),
    SHOW_UPDATE_PERIODICAL(ShowUpdatePeriodicalCommand.INSTANCE),
    SHOW_SUBSCRIBE(ShowSubscribeCommand.INSTANCE),

    DELETE_PERIODICAL(DeletePeriodicalCommand.INSTANCE),
    UPDATE_PERIODICAL(UpdatePeriodicalCommand.INSTANCE),
    ADD_PERIODICAL(AddPeriodicalCommand.INSTANCE),
    DEFAULT(ShowGuestPageCommand.INSTANCE);

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
