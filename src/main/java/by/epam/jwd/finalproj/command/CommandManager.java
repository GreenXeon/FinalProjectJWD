package by.epam.jwd.finalproj.command;

import by.epam.jwd.finalproj.command.action.admin.AddPeriodicalCommand;
import by.epam.jwd.finalproj.command.action.admin.DeletePeriodicalCommand;
import by.epam.jwd.finalproj.command.action.admin.UpdatePeriodicalCommand;
import by.epam.jwd.finalproj.command.action.user.SubscribeCommand;
import by.epam.jwd.finalproj.command.action.user.TopUpBalanceCommand;
import by.epam.jwd.finalproj.command.action.user.UpdateUserCommand;
import by.epam.jwd.finalproj.command.page.user.ShowProfilePageCommand;
import by.epam.jwd.finalproj.command.page.user.ShowSubscribeCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowUpdatePeriodicalCommand;
import by.epam.jwd.finalproj.command.page.*;
import by.epam.jwd.finalproj.command.page.admin.ShowAddPeriodicalCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowAllUsersCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.action.LoginCommand;
import by.epam.jwd.finalproj.command.action.LogoutCommand;
import by.epam.jwd.finalproj.command.action.SignUpCommand;
import by.epam.jwd.finalproj.command.page.user.ShowUpdatePageCommand;

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
    SHOW_PROFILE_USER(ShowProfilePageCommand.INSTANCE),
    SHOW_UPDATE_USER(ShowUpdatePageCommand.INSTANCE),
    SHOW_SUBSCRIBE(ShowSubscribeCommand.INSTANCE),
    TOP_UP_BALANCE(TopUpBalanceCommand.INSTANCE),
    SUBSCRIBE(SubscribeCommand.INSTANCE),

    UPDATE_USER(UpdateUserCommand.INSTANCE),

    DELETE_PERIODICAL(DeletePeriodicalCommand.INSTANCE),
    UPDATE_PERIODICAL(UpdatePeriodicalCommand.INSTANCE),
    ADD_PERIODICAL(AddPeriodicalCommand.INSTANCE),
    DEFAULT(ShowErrorPageCommand.INSTANCE);

    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command retrieveCommand(String name){
        for(CommandManager command : values()){
            if (command.name().equalsIgnoreCase(name)){
                return command.command;
            }
        }
        return DEFAULT.command;
    }
}
