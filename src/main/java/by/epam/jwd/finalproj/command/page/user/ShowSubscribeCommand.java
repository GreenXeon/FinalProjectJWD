package by.epam.jwd.finalproj.command.page.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.exception.CommandException;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalServiceImpl;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum ShowSubscribeCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ShowSubscribeCommand.class);

    private final PeriodicalServiceImpl periodicalService;
    private final UserServiceImpl userService;

    ShowSubscribeCommand(){
        this.periodicalService = PeriodicalServiceImpl.INSTANCE;
        this.userService = UserServiceImpl.INSTANCE;
    }

    private static final Route SHOW_SUBSCRIBE_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/user/showSubscription.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        if (request.getParameterValues(SELECTED) == null){
            request.setAttribute(ERROR, "Choose periodical!");
            return ShowMainPageCommand.INSTANCE.execute(request, response);
        }
        String[] selectedIds = request.getParameterValues(SELECTED);
        List<PeriodicalDto> periodicalsToSubscribe = new ArrayList<>();
        try{
            for(String selectedPeriodical : selectedIds){
                int id = Integer.parseInt(selectedPeriodical);
                PeriodicalDto periodical = periodicalService.findById(id).orElse(null);
                if (periodical == null) {
                    request.setAttribute(ERROR, "Periodical is not available");
                    return ShowMainPageCommand.INSTANCE.execute(request, response);
                }
                periodicalsToSubscribe.add(periodical);
            }
            request.setSessionAttribute(SUBSCRIBE_PERIODICALS, periodicalsToSubscribe);
            BigDecimal totalCost = BigDecimal.ZERO;
            for (PeriodicalDto periodical : periodicalsToSubscribe){
                totalCost = totalCost.add(periodical.getSubCost());
            }
            request.setSessionAttribute(TOTAL_COST, totalCost);
            Optional<UserDto> user = userService.findByLogin((String)request.getSessionAttribute(SESSION_USER_LOGIN));
            if (!user.isPresent()){
                throw new CommandException("User is not found");
            }
            request.setAttribute(USER, user.get());
            return SHOW_SUBSCRIBE_RESPONSE;
        } catch (CommandException e){
            logger.error(e.getMessage());
            return ShowMainPageCommand.INSTANCE.execute(request, response);
        }
    }
}
