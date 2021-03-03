package by.epam.jwd.finalproj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WrappingRequestContext implements RequestContext{
    private final HttpServletRequest request;

    private WrappingRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String name, Object obj) {
        request.setAttribute(name, obj);
    }

    @Override
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    @Override
    public void invalidateSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void setSessionAttribute(String name, Object value) {
        final HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    public static RequestContext of(HttpServletRequest request) {
        return new WrappingRequestContext(request);
    }
}
