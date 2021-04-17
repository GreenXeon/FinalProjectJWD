package by.epam.jwd.finalproj.command.page.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum ShowMainAdminPageCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    private final Logger logger = LogManager.getLogger(ShowMainAdminPageCommand.class);

    ShowMainAdminPageCommand(){
        this.periodicalService = PeriodicalService.INSTANCE;
    }

    private static final Route MAIN_ADMIN_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/admin/mainAdminPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        String phraseToFind = request.getParameter(FINDER);
        if (phraseToFind == null || phraseToFind.isEmpty()){
            final List<PeriodicalDto> periodicals = periodicalService.findAll().orElse(Collections.emptyList());
            request.setAttribute(PERIODICALS, periodicals);
            return MAIN_ADMIN_RESPONSE;
        }
        PeriodicalService periodicalService = PeriodicalService.INSTANCE;
        List<PeriodicalDto> foundPeriodicals = periodicalService.findPeriodicalByPhrase(phraseToFind);
        request.setAttribute(PERIODICALS, foundPeriodicals);
        return MAIN_ADMIN_RESPONSE;
    }
}
