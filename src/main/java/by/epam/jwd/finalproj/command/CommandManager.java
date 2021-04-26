package by.epam.jwd.finalproj.command;

import by.epam.jwd.finalproj.command.action.*;
import by.epam.jwd.finalproj.command.action.admin.*;
import by.epam.jwd.finalproj.command.action.user.SubscribeCommand;
import by.epam.jwd.finalproj.command.action.user.TopUpBalanceCommand;
import by.epam.jwd.finalproj.command.action.UpdateUserCommand;
import by.epam.jwd.finalproj.command.page.user.ShowOrdersCommand;
import by.epam.jwd.finalproj.command.page.ShowProfilePageCommand;
import by.epam.jwd.finalproj.command.page.user.ShowSubscribeCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowUpdatePeriodicalCommand;
import by.epam.jwd.finalproj.command.page.*;
import by.epam.jwd.finalproj.command.page.admin.ShowAddPeriodicalCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowAllUsersCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.page.ShowUpdatePageCommand;

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
    SHOW_ORDERS(ShowOrdersCommand.INSTANCE),
    TOP_UP_BALANCE(TopUpBalanceCommand.INSTANCE),
    SUBSCRIBE(SubscribeCommand.INSTANCE),
    SHOW_CHANGE_PASSWORD(ShowChangePasswordCommand.INSTANCE),
    CHANGE_PASSWORD(ChangePasswordCommand.INSTANCE),

    CHANGE_LANGUAGE(ChangeLanguageCommand.INSTANCE),

    SHOW_SUCCESS_PAGE(ShowSuccessPageCommand.INSTANCE),

    BAN_USER(BanUserCommand.INSTANCE),
    UNBAN_USER(UnbanUserCommand.INSTANCE),
    MAKE_ADMIN(MakeUserAdminCommand.INSTANCE),
    MAKE_USER(MakeAdminUserCommand.INSTANCE),

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

    public static String retrieveCommandName(Command command){
        for (CommandManager commandWrap : values()){
            if (commandWrap.getCommand().equals(command)){
                return commandWrap.name();
            }
        }
        return DEFAULT.name();
    }
}
