package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.admin.ShowAddPeriodicalCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.exception.CommandException;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalType;
import by.epam.jwd.finalproj.service.impl.PeriodicalServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import static by.epam.jwd.finalproj.validator.Validator.*;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum AddPeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalServiceImpl periodicalService;

    AddPeriodicalCommand(){
        this.periodicalService = PeriodicalServiceImpl.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(AddPeriodicalCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            final String name = String.valueOf(request.getParameter(PERIODICAL_NAME));
            final String author = request.getParameter(PERIODICAL_AUTHOR);
            final LocalDate publishDate = LocalDate.parse(request.getParameter(PERIODICAL_PUBLISH_DATE));
            final PeriodicalType type = PeriodicalType.findById(Integer.parseInt(request.getParameter(PERIODICAL_TYPE)));
            final BigDecimal cost = new BigDecimal(request.getParameter(PERIODICAL_COST)).setScale(2, RoundingMode.UNNECESSARY);
            final String publisher = request.getParameter(PERIODICAL_PUBLISHER);
            Route addPeriodicalPage = ShowAddPeriodicalCommand.INSTANCE.execute(request, response);
            if(!isValidPeriodicalName(name) || !isValidPeriodicalAuthor(author) || !isValidPeriodicalCost(cost.toString()) ||
                !isValidPeriodicalPublisher(publisher)){
                request.setAttribute(ERROR, "Check input data!");
                return addPeriodicalPage;
            }
            if (periodicalService.findByName(name).isPresent()) {
                request.setAttribute(ERROR, "Periodical with this name exists!");
                return addPeriodicalPage;
            }
            PeriodicalDto periodicalToAdd = new PeriodicalDto.Builder()
                    .withName(name)
                    .withAuthor(author)
                    .withPublishDate(publishDate)
                    .withType(type)
                    .withCost(cost)
                    .withPublisher(publisher)
                    .build();
            Optional<PeriodicalDto> newPeriodical = periodicalService.save(periodicalToAdd);
            if (newPeriodical.isPresent()) {
                return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
            } else {
                throw new CommandException("Periodical was not saved!");
            }
        } catch (NumberFormatException | NullPointerException e){
            logger.error(e.getMessage());
            request.setAttribute(ERROR, "Check input data!");
            return ShowAddPeriodicalCommand.INSTANCE.execute(request, response);
        } catch (CommandException e){
            logger.error(e.getMessage());
            request.setAttribute(ERROR, e.getMessage());
            return ShowAddPeriodicalCommand.INSTANCE.execute(request, response);
        }
    }
}
