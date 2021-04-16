package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowAddPeriodicalCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowUpdatePeriodicalCommand;
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
import static by.epam.jwd.finalproj.validator.Validator.isValidPeriodicalPublisher;

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
            final int id = Integer.parseInt(request.getParameter("periodicalId"));
            final String name = String.valueOf(request.getParameter("name"));
            final String author = String.valueOf(request.getParameter("author"));
            final LocalDate publishDate = LocalDate.parse(request.getParameter("publishDate"));
            final PeriodicalType type = PeriodicalType.findById(Integer.parseInt(request.getParameter("type")));
            final BigDecimal cost = new BigDecimal(request.getParameter("cost")).setScale(2, RoundingMode.UNNECESSARY);
            final String publisher = String.valueOf(request.getParameter("publisher"));

            if (!isValidPeriodicalName(name) || !isValidPeriodicalAuthor(author) || !isValidPeriodicalCost(cost.toString()) ||
                    !isValidPeriodicalPublisher(publisher)) {
                request.setAttribute("errorMessage", "Check input data!");
                return ShowUpdatePeriodicalCommand.INSTANCE.execute(request, response);
            }

            if (periodicalService.findByName(name).isPresent() && !periodicalService.findById(id).get().getName().equalsIgnoreCase(name)) {
                request.setAttribute("errorMessage", "Periodical with this name exists!");
                request.setAttribute("periodicalName", periodicalService.findById(id).get().getName());
                return ShowUpdatePeriodicalCommand.INSTANCE.execute(request, response);
            }

            final PeriodicalDto periodicalToChange = new PeriodicalDto(id, name, author, publishDate, type, cost, publisher);
            Optional<PeriodicalDto> updatedPeriodical = periodicalService.update(periodicalToChange);
            if (!updatedPeriodical.isPresent()) {
                logger.error("Periodical with id " + id + " is not updated");
                //todo: set errorMessage
                return ShowErrorPageCommand.INSTANCE.execute(request, response);
            }
            logger.info("Periodical with id " + id + " is updated");
            return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
        } catch (NullPointerException | NumberFormatException e){
            logger.error(e.getMessage());
            request.setAttribute("errorMessage", "Check input data!");
            return ShowUpdatePeriodicalCommand.INSTANCE.execute(request, response);
        }
    }
}
