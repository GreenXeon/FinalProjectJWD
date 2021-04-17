package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum FindPeriodicalCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(FindPeriodicalCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        String phraseToFind = request.getParameter(FINDER);
        String userRole = (String) request.getSessionAttribute(SESSION_USER_ROLE);
        int userId = (int) request.getSessionAttribute(SESSION_USER_ID);
        PeriodicalService periodicalService = PeriodicalService.INSTANCE;
        List<PeriodicalDto> foundPeriodicals = periodicalService.findPeriodicalByPhrase(userId, phraseToFind);
        request.setAttribute(PERIODICALS, foundPeriodicals);
        if (userRole.equalsIgnoreCase("ADMIN")){
            return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
        }
        return ShowMainPageCommand.INSTANCE.execute(request, response);
    }
}
