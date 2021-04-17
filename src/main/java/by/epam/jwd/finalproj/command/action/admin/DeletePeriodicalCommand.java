package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.exception.CommandException;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import by.epam.jwd.finalproj.util.ParameterNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum DeletePeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    DeletePeriodicalCommand(){
        this.periodicalService = PeriodicalService.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(DeletePeriodicalCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            final int periodicalId = Integer.parseInt(request.getParameter(PERIODICAL_ID));
            boolean deletingResult = periodicalService.delete(periodicalId);
            if (!deletingResult) {
                throw new CommandException("Periodical was not deleted");
            }
        } catch (NumberFormatException e){
            logger.error(e.getMessage());
            request.setAttribute(ERROR, "Check periodical id!");
        } catch (CommandException e){
            logger.error(e.getMessage());
            request.setAttribute(ERROR, e.getMessage());
        }
        return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
    }
}
