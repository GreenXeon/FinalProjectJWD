package by.epam.jwd.finalproj.command;

import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;

public class AccessValidator {
    static boolean hasAdminAccess(RequestContext request){
        String userRole = String.valueOf(request.getCookieByName("role").get().getValue());
        if (!userRole.equalsIgnoreCase("admin")){
            //return ShowErrorPageCommand.INSTANCE.execute(request);
        }
        return false;
    }

    static boolean hasUserAccess(RequestContext request){
        return false;
    }
}
