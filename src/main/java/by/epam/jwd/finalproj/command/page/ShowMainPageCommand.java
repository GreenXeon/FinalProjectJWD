package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;

import java.util.Collections;
import java.util.List;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum ShowMainPageCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    ShowMainPageCommand(){
        this.periodicalService = PeriodicalService.INSTANCE;
    }


    private static final Route MAIN_PAGE_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/user/mainUserPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        int userId = (int) request.getSessionAttribute(SESSION_USER_ID);
        String phraseToFind = request.getParameter(FINDER);
        if (phraseToFind == null || phraseToFind.isEmpty()){
            final List<PeriodicalDto> periodicals = periodicalService.findForCurrentUser(userId).orElse(Collections.emptyList());
            request.setAttribute(PERIODICALS, periodicals);
            return MAIN_PAGE_RESPONSE;
        }
        PeriodicalService periodicalService = PeriodicalService.INSTANCE;
        List<PeriodicalDto> foundPeriodicals = periodicalService.findPeriodicalByPhrase(userId, phraseToFind);
        request.setAttribute(PERIODICALS, foundPeriodicals);
        return MAIN_PAGE_RESPONSE;
    }
}
