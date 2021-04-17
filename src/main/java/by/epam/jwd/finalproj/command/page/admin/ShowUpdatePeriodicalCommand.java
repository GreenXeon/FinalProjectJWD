package by.epam.jwd.finalproj.command.page.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum ShowUpdatePeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    ShowUpdatePeriodicalCommand(){
        this.periodicalService = PeriodicalService.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(ShowUpdatePeriodicalCommand.class);

    private static final Route SHOW_UPDATE_PER_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/admin/updatePeriodicalPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        String periodicalName;
        if(request.getParameter(PERIODICAL_NAME_REQUEST) == null){
            periodicalName = (String) request.getAttribute(PERIODICAL_NAME_REQUEST);
        } else {
            periodicalName = request.getParameter(PERIODICAL_NAME_REQUEST);
        }
        Optional<PeriodicalDto> periodical = periodicalService.findByName(periodicalName);
        if (periodical.isPresent()){
            request.setAttribute(PERIODICAL, periodical.get());
            return SHOW_UPDATE_PER_RESPONSE;
        }
        logger.info("Periodical " + periodicalName + " is not found");
        return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
    }
}
