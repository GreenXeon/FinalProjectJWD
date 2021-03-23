package by.epam.jwd.finalproj.command.page.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public enum ShowSubscribeCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ShowSubscribeCommand.class);

    private final PeriodicalService periodicalService;

    ShowSubscribeCommand(){
        this.periodicalService = new PeriodicalService();
    }

    private static final ResponseContext SHOW_SUBSCRIBE_RESPONSE = new ResponseContext() {
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
    public ResponseContext execute(RequestContext request) {
        logger.info("ShowSubscribe processing");
        logger.info(request.getParameterValues("selected"));
        if (request.getParameterValues("selected") == null){
            logger.info("array is empty");
            request.setAttribute("errorMessage", "Choose periodical!");
            return ShowMainPageCommand.INSTANCE.execute(request);
        }
        String[] selectedPeriodicalsId = request.getParameterValues("selected");
        List<PeriodicalDto> periodicalsToSubscribe = new ArrayList<>();
        try{
            for(String selectedPeriodical : selectedPeriodicalsId){
                int id = Integer.parseInt(selectedPeriodical);
                PeriodicalDto periodicalToSubscribe = periodicalService.findById(id).orElse(null);
                if (periodicalToSubscribe == null) {
                    throw new Exception("Periodical is not found");
                }
                periodicalsToSubscribe.add(periodicalToSubscribe);
                logger.info("Periodical " + periodicalToSubscribe.getName() + " is added to list");
            }
            logger.info(periodicalsToSubscribe);
            request.setAttribute("subscribePeriodicals", periodicalsToSubscribe);
            return SHOW_SUBSCRIBE_RESPONSE;
        } catch (Exception e){
            logger.error(e.getMessage());
            return ShowErrorPageCommand.INSTANCE.execute(request);
        }
    }
}
