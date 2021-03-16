package by.epam.jwd.finalproj.command;

import javax.servlet.http.Cookie;

public interface RequestContext {
    void setAttribute(String name, Object obj);
    Object getAttribute(String name);
    void invalidateSession();
    void setSessionAttribute(String name, Object value);
    String getParameter(String name);
    Cookie[] getCookies();
}
