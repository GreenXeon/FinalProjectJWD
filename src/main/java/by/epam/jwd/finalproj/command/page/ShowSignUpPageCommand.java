package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;

public enum ShowSignUpPageCommand implements Command {
    INSTANCE;

    private static final ResponseContext SIGNUP_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/jsp/signUp.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        return SIGNUP_PAGE_RESPONSE;
    }
}
