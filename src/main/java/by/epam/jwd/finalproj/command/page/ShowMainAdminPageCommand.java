package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public enum ShowMainAdminPageCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    private final Logger logger = LogManager.getLogger(ShowMainAdminPageCommand.class);

    ShowMainAdminPageCommand(){
        this.periodicalService = new PeriodicalService();
    }

    private static final ResponseContext MAIN_ADMIN_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/jsp/admin/mainAdminPage.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        final List<PeriodicalDto> periodicals = periodicalService.findAll().orElse(Collections.emptyList());
        request.setAttribute("periodicals", periodicals);
        logger.info(periodicals.get(0).getName());
        return MAIN_ADMIN_RESPONSE;
    }
}
