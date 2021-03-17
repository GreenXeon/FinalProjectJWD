package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowGuestPageCommand;

import javax.servlet.http.Cookie;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext request) {
        request.invalidateSession();
        Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                cookie.setMaxAge(0);
            }
        return ShowGuestPageCommand.INSTANCE.execute(request);
    }
}
