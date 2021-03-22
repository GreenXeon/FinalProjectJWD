package by.epam.jwd.finalproj.command.page.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum ShowAllUsersCommand implements Command {
    INSTANCE;

    private final UserService userService;

    ShowAllUsersCommand(){
        this.userService = new UserService();
    }

    private static final ResponseContext SHOW_USERS_RESPONSE = new ResponseContext() {
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
    public ResponseContext execute(RequestContext request) {
        List<UserDto> allUsers = userService.findAll().orElse(Collections.emptyList());
        request.setAttribute("users", allUsers);
        return SHOW_USERS_RESPONSE;
    }
}
