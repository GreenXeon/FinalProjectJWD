package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.user.ShowOrdersCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ShowChangePasswordCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ShowOrdersCommand.class);

    private static final Route SHOW_CHANGE_PASS_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/showChangePassword.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        return SHOW_CHANGE_PASS_RESPONSE;
    }
}
