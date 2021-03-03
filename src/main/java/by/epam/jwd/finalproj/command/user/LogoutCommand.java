package by.epam.jwd.finalproj.command.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext request) {
        return null;
    }
}
