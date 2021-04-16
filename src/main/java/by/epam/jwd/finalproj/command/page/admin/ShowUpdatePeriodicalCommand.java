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
        if(request.getParameter("periodicalName") == null){
            periodicalName = (String) request.getAttribute("periodicalName");
        } else {
            periodicalName = request.getParameter("periodicalName");
        }
        logger.info(periodicalName);
        Optional<PeriodicalDto> periodical = periodicalService.findByName(periodicalName);
        if (periodical.isPresent()){
            request.setAttribute("periodical", periodical.get());
            return SHOW_UPDATE_PER_RESPONSE;
        }
        logger.info("periodical " + periodicalName + " is not found");
        return ShowErrorPageCommand.INSTANCE.execute(request, response);
    }
}
