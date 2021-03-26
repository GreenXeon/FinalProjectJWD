package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowGuestPageCommand;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;

import javax.servlet.http.Cookie;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        request.invalidateSession();
        Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                cookie.setMaxAge(0);
                cookie.setValue("");
                response.addCookie(cookie);
            }
        return ShowLoginPageCommand.INSTANCE.execute(request, response);
    }
}
