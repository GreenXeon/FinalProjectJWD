package by.epam.jwd.finalproj.command.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext request) {
        request.invalidateSession();
        return ShowMainPageCommand.INSTANCE.execute(request);
    }
}
