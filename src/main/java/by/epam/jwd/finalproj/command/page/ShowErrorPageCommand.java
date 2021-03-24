package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;

public enum ShowErrorPageCommand implements Command {
    INSTANCE;

    private static final Route ERROR_PAGE_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        return ERROR_PAGE_RESPONSE;
    }
}
