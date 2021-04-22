package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalServiceImpl;

import java.util.Collections;
import java.util.List;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum ShowGuestPageCommand implements Command {
    INSTANCE;

    private final PeriodicalServiceImpl periodicalService;

    ShowGuestPageCommand(){
        this.periodicalService = PeriodicalServiceImpl.INSTANCE;
    }

    private static final Route GUEST_PAGE_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/guest.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        String phraseToFind = request.getParameter(FINDER);
        final List<PeriodicalDto> periodicals = periodicalService.findAll().orElse(Collections.emptyList());
        if (phraseToFind == null || phraseToFind.isEmpty()){
            request.setAttribute(PERIODICALS, periodicals);
            return GUEST_PAGE_RESPONSE;
        }
        PeriodicalServiceImpl periodicalService = PeriodicalServiceImpl.INSTANCE;
        List<PeriodicalDto> foundPeriodicals = periodicalService.findPeriodicalByPhrase(phraseToFind);
        if (foundPeriodicals.isEmpty()){
            request.setAttribute(ERROR, "Periodicals are not found");
            request.setAttribute(PERIODICALS, periodicals);
        } else {
            request.setAttribute(PERIODICALS, foundPeriodicals);
        }
        return GUEST_PAGE_RESPONSE;
    }
}
