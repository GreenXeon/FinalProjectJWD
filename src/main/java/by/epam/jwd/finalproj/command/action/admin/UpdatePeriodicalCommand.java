package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
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

public enum UpdatePeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    UpdatePeriodicalCommand(){
        this.periodicalService = new PeriodicalService();
    }

    private final Logger logger = LogManager.getLogger(UpdatePeriodicalCommand.class);

    @Override
    public ResponseContext execute(RequestContext request) {
        //todo: add server validation
        final int id = Integer.parseInt(request.getParameter("periodicalId"));
        final String name = String.valueOf(request.getParameter("name"));
        final String author = String.valueOf(request.getParameter("author"));
        final LocalDate publishDate = LocalDate.parse(request.getParameter("publishDate"));
        final PeriodicalType type = PeriodicalType.findById(Integer.parseInt(request.getParameter("type")));
        final BigDecimal cost = new BigDecimal(request.getParameter("cost")).setScale(2, RoundingMode.UNNECESSARY);
        final String publisher = String.valueOf(request.getParameter("publisher"));
        //todo: validation check

        final PeriodicalDto periodicalToChange = new PeriodicalDto(id, name, author, publishDate, type, cost, publisher);
        Optional<PeriodicalDto> updatedPeriodical = periodicalService.update(periodicalToChange);
        if (!updatedPeriodical.isPresent()){
            logger.error("Periodical '" + name + "' with id " + id + " is not updated");
            //todo: set errorMessage
            return ShowErrorPageCommand.INSTANCE.execute(request);
        }
        logger.info("Periodical '" + name + "' with id " + id + " is updated");
        return ShowMainAdminPageCommand.INSTANCE.execute(request);
    }
}
