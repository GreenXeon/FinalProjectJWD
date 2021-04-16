package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowAddPeriodicalCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
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

public enum AddPeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    AddPeriodicalCommand(){
        this.periodicalService = PeriodicalService.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(AddPeriodicalCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            final String name = String.valueOf(request.getParameter("name"));
            final String author = request.getParameter("author");
            final LocalDate publishDate = LocalDate.parse(request.getParameter("publishDate"));
            final PeriodicalType type = PeriodicalType.findById(Integer.parseInt(request.getParameter("type")));
            final BigDecimal cost = new BigDecimal(request.getParameter("cost")).setScale(2, RoundingMode.UNNECESSARY);
            final String publisher = request.getParameter("publisher");

            if(!isValidPeriodicalName(name) || !isValidPeriodicalAuthor(author) || !isValidPeriodicalCost(cost.toString()) ||
                !isValidPeriodicalPublisher(publisher)){
                request.setAttribute("errorMessage", "Check input data!");
                return ShowAddPeriodicalCommand.INSTANCE.execute(request, response);
            }

            if (periodicalService.findByName(name).isPresent()) {
                request.setAttribute("errorMessage", "Periodical with this name exists!");
                return ShowAddPeriodicalCommand.INSTANCE.execute(request, response);
            }

            Route result;
            PeriodicalDto periodicalToAdd = new PeriodicalDto(0, name, author, publishDate, type, cost, publisher);
            Optional<PeriodicalDto> newPeriodical = periodicalService.save(periodicalToAdd);
            if (newPeriodical.isPresent()) {
                logger.info("Periodical is saved");
                result = ShowMainAdminPageCommand.INSTANCE.execute(request, response);
            } else {
                logger.error("Periodical is not saved");
                result = ShowErrorPageCommand.INSTANCE.execute(request, response);
            }
            return result;
        } catch (NumberFormatException | NullPointerException e){
            logger.error(e.getMessage());
            request.setAttribute("errorMessage", "Check input data!");
            return ShowAddPeriodicalCommand.INSTANCE.execute(request, response);
        }
    }
}
