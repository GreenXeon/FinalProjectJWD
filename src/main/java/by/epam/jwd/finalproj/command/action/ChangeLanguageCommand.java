package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowProfilePageCommand;
import by.epam.jwd.finalproj.model.Languages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;

public enum ChangeLanguageCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);

    private final int COOKIE_FOR_MONTH = 60 * 60 * 24 * 30;

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try{
            int langId = Integer.parseInt(request.getParameter("lang"));
            String locale = Languages.getLangById(langId);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("locale")) {
                        cookie.setValue(locale);
                        cookie.setMaxAge(COOKIE_FOR_MONTH);
                        response.addCookie(cookie);
                    }
                }
            }
        } catch (NumberFormatException e){
            logger.error(e.getMessage());
            request.setAttribute("errorMessage", "Enter correct data!");
        }
        return ShowProfilePageCommand.INSTANCE.execute(request, response);
    }
}
