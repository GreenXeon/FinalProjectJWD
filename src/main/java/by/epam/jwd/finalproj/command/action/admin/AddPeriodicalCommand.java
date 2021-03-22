package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowAddPeriodicalCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalType;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public enum AddPeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    AddPeriodicalCommand(){
        this.periodicalService = new PeriodicalService();
    }

    private final Logger logger = LogManager.getLogger(AddPeriodicalCommand.class);

    @Override
    public ResponseContext execute(RequestContext request) {
        final String name = String.valueOf(request.getParameter("name"));
        final String author = String.valueOf(request.getParameter("author"));
        final LocalDate publishDate = LocalDate.parse(request.getParameter("publishDate"));
        final PeriodicalType type = PeriodicalType.findById(Integer.parseInt(request.getParameter("type")));
        final BigDecimal cost = new BigDecimal(request.getParameter("cost")).setScale(2);
        final String publisher = String.valueOf(request.getParameter("publisher"));
        //todo: validation check
        logger.info(publishDate);
        ResponseContext result = null;
        PeriodicalDto periodicalToAdd = new PeriodicalDto(0, name, author, publishDate, type, cost, publisher);
        Optional<PeriodicalDto> newPeriodical = periodicalService.save(periodicalToAdd);
        if(newPeriodical.isPresent()){
            logger.info("Periodical is saved");
            result = ShowMainAdminPageCommand.INSTANCE.execute(request);
        }
        else {
            logger.error("Periodical is not saved");
            result = ShowErrorPageCommand.INSTANCE.execute(request);
        }
        result = ShowAddPeriodicalCommand.INSTANCE.execute(request);
        return result;
    }
}