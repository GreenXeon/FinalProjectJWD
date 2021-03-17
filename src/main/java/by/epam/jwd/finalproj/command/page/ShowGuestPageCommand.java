package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.CommonService;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;

import java.util.Collections;
import java.util.List;

public enum ShowGuestPageCommand implements Command {
    INSTANCE;

    private final PeriodicalService periodicalService;

    ShowGuestPageCommand(){
        this.periodicalService = new PeriodicalService();
    }

    private static final ResponseContext GUEST_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/guest.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        final List<PeriodicalDto> periodicals = periodicalService.findAll().orElse(Collections.emptyList());
        request.setAttribute("periodicals", periodicals);
        return GUEST_PAGE_RESPONSE;
    }
}
