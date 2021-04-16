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

public enum FindPeriodicalCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(FindPeriodicalCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        String phraseToFind = request.getParameter("finder");
        String userRole = (String) request.getSessionAttribute("role");
        int userId = (int) request.getSessionAttribute("userId");
        //todo validation
        PeriodicalService periodicalService = PeriodicalService.INSTANCE;
        List<PeriodicalDto> foundPeriodicals = periodicalService.findPeriodicalByPhrase(userId, phraseToFind);
        logger.info(foundPeriodicals.size());
        request.setAttribute("periodicals", foundPeriodicals);
        if (userRole.equalsIgnoreCase("ADMIN")){
            return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
        }
        return ShowMainPageCommand.INSTANCE.execute(request, response);
    }
}
