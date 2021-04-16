package by.epam.jwd.finalproj.command.page.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;

import java.util.Collections;
import java.util.List;

public enum ShowAllUsersCommand implements Command {
    INSTANCE;

    private final UserService userService;

    ShowAllUsersCommand(){
        this.userService = UserService.INSTANCE;
    }

    private static final Route SHOW_USERS_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/admin/showAllUsersPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        String phraseToFind = request.getParameter("finder");
        if (phraseToFind == null || phraseToFind.isEmpty()){
            List<UserDto> allUsers = userService.findAll().orElse(Collections.emptyList());
            request.setAttribute("users", allUsers);
            return SHOW_USERS_RESPONSE;
        }
        List<UserDto> foundUsers = userService.findByPhraseLogin(phraseToFind);
        request.setAttribute("users", foundUsers);
        return SHOW_USERS_RESPONSE;

    }
}
