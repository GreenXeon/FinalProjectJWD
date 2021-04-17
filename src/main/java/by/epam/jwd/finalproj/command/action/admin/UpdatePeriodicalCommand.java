package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowUpdatePeriodicalCommand;
import by.epam.jwd.finalproj.exception.CommandException;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalType;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;
import static by.epam.jwd.finalproj.validator.Validator.*;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum UpdatePeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    UpdatePeriodicalCommand(){
        this.periodicalService = PeriodicalService.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(UpdatePeriodicalCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            final int id = Integer.parseInt(request.getParameter(PERIODICAL_ID));
            final String name = String.valueOf(request.getParameter(PERIODICAL_NAME));
            final String author = String.valueOf(request.getParameter(PERIODICAL_AUTHOR));
            final LocalDate publishDate = LocalDate.parse(request.getParameter(PERIODICAL_PUBLISH_DATE));
            final PeriodicalType type = PeriodicalType.findById(Integer.parseInt(request.getParameter(PERIODICAL_TYPE)));
            final BigDecimal cost = new BigDecimal(request.getParameter(PERIODICAL_COST)).setScale(2, RoundingMode.UNNECESSARY);
            final String publisher = String.valueOf(request.getParameter(PERIODICAL_PUBLISHER));
            if (!isValidPeriodicalName(name) || !isValidPeriodicalAuthor(author) || !isValidPeriodicalCost(cost.toString()) ||
                    !isValidPeriodicalPublisher(publisher)) {
                request.setAttribute(ERROR, "Check input data!");
                throw new CommandException("Incorrect data input");
            }
            if (periodicalService.findByName(name).isPresent() && !periodicalService.findById(id).get().getName().equalsIgnoreCase(name)) {
                request.setAttribute(ERROR, "Periodical with this name exists!");
                request.setAttribute(PERIODICAL_NAME_REQUEST, periodicalService.findById(id).get().getName());
                throw new CommandException("Attempt to add periodical with existing name");
            }
            final PeriodicalDto periodicalToChange = new PeriodicalDto(id, name, author, publishDate, type, cost, publisher);
            Optional<PeriodicalDto> updatedPeriodical = periodicalService.update(periodicalToChange);
            if (!updatedPeriodical.isPresent()) {
                request.setAttribute(ERROR, "Periodical is not updated");
                throw new CommandException("Unsuccessful attempt to update periodical");
            }
            return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
        } catch (NullPointerException | NumberFormatException e){
            logger.error(e.getMessage());
            request.setAttribute(ERROR, "Check input data!");
            return ShowUpdatePeriodicalCommand.INSTANCE.execute(request, response);
        } catch (CommandException e){
            logger.error(e.getMessage());
            return ShowUpdatePeriodicalCommand.INSTANCE.execute(request, response);
        }
    }
}
