package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;

public enum DeletePeriodicalCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    DeletePeriodicalCommand(){
        this.periodicalService = new PeriodicalService();
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        final int periodicalId = Integer.parseInt(request.getParameter("periodicalId"));
        boolean deletingResult = periodicalService.delete(periodicalId);
        if (!deletingResult){
            return ShowErrorPageCommand.INSTANCE.execute(request);
        }
        return ShowMainAdminPageCommand.INSTANCE.execute(request);
    }
}
