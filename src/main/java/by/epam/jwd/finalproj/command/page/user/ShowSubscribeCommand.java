package by.epam.jwd.finalproj.command.page.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum ShowSubscribeCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ShowSubscribeCommand.class);

    private final PeriodicalService periodicalService;
    private final UserService userService;

    ShowSubscribeCommand(){
        this.periodicalService = PeriodicalService.INSTANCE;
        this.userService = UserService.INSTANCE;
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
        logger.info("ShowSubscribe processing...");
        if (request.getParameterValues("selected") == null){
            logger.warn("Array of periodicals is empty!");
            request.setAttribute("errorMessage", "Choose periodical!");
            return ShowMainPageCommand.INSTANCE.execute(request, response);
        }
        String[] selectedIds = request.getParameterValues("selected");
        List<PeriodicalDto> periodicalsToSubscribe = new ArrayList<>();
        try{
            for(String selectedPeriodical : selectedIds){
                int id = Integer.parseInt(selectedPeriodical);
                PeriodicalDto periodical = periodicalService.findById(id).orElse(null);
                if (periodical == null) {
                    logger.warn("Periodical is not available");
                    request.setAttribute("errorMessage", "Periodical is not available");
                    return ShowMainPageCommand.INSTANCE.execute(request, response);
                }
                periodicalsToSubscribe.add(periodical);
            }
            request.setSessionAttribute("subscribePeriodicals", periodicalsToSubscribe);
            BigDecimal totalCost = BigDecimal.ZERO;
            for (PeriodicalDto periodical : periodicalsToSubscribe){
                totalCost = totalCost.add(periodical.getSubCost());
            }
            request.setSessionAttribute("totalCost", totalCost);
            Optional<UserDto> user = userService.findByLogin((String)request.getSessionAttribute("login"));
            if (!user.isPresent()){
                throw new Exception("User is not found");
            }
            request.setAttribute("user", user.get());
            return SHOW_SUBSCRIBE_RESPONSE;
        } catch (Exception e){
            logger.error(e.getMessage());
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }
    }
}
