package by.epam.jwd.finalproj.command;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface RequestContext {
    void setAttribute(String name, Object obj);
    Object getAttribute(String name);
    void invalidateSession();
    void setSessionAttribute(String name, Object value);
    Object getSessionAttribute(String name);
    String getParameter(String name);
    String[] getParameterValues(String name);
    Cookie[] getCookies();
    Optional<Cookie> getCookieByName(String name);
}
