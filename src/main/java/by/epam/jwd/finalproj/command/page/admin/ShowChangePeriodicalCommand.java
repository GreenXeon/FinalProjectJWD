package by.epam.jwd.finalproj.command.page.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public enum ShowChangePeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    ShowChangePeriodicalCommand(){
        this.periodicalService = new PeriodicalService();
    }

    private final Logger logger = LogManager.getLogger(ShowChangePeriodicalCommand.class);

    private static final ResponseContext SHOW_CHANGE_PER_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/admin/changePeriodicalPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        String periodicalName = request.getParameter("periodicalName");
        logger.info(periodicalName);
        Optional<PeriodicalDto> periodical = periodicalService.findByName(periodicalName);
        if (periodical.isPresent()){
            logger.info(periodicalName + " - " + periodical.get().getName());
            request.setAttribute("periodical", periodical.get());
            return SHOW_CHANGE_PER_RESPONSE;
        }
        logger.info("periodical " + periodicalName + " is not found");
        return ShowErrorPageCommand.INSTANCE.execute(request);
    }
}
