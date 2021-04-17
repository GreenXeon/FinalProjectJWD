package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowGuestPageCommand;
import by.epam.jwd.finalproj.model.Language;

import javax.servlet.http.Cookie;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum LogoutCommand implements Command {
    INSTANCE;

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                if(!cookie.getName().equals(LOCALE_COOKIE_NAME)) {
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    cookie.setPath("/");
                    response.addCookie(cookie);
                } else {
                    cookie.setValue(Language.en.name());
                    response.addCookie(cookie);
                }
            }
        request.invalidateSession();
        return ShowGuestPageCommand.INSTANCE.execute(request, response);
    }
}
