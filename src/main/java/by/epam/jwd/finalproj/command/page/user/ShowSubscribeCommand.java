package by.epam.jwd.finalproj.command.page.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum ShowSubscribeCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ShowSubscribeCommand.class);

    private final PeriodicalService periodicalService;
    private final UserService userService;

    ShowSubscribeCommand(){
        this.periodicalService = new PeriodicalService();
        this.userService = new UserService();
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
        logger.info("ShowSubscribe processing");
        if (request.getParameterValues("selected") == null){
            logger.info("array is empty");
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
                    throw new Exception("Periodical is not found");
                }
                periodicalsToSubscribe.add(periodical);
                logger.info("Periodical " + periodical.getName() + " is added to list");
            }
            logger.info(periodicalsToSubscribe);
            request.setAttribute("subscribePeriodicals", periodicalsToSubscribe);
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
