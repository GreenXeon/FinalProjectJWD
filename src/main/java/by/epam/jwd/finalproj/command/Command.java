package by.epam.jwd.finalproj.command;

/**
 * Interface describes basic command's method for executing and static method for
 * retrieving {@link Command} by name
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface Command {
    /**
     *Static method that retrieves {@link Command} using name got as parameter
     *
     * @param name {@link Command}'s name for retrieving
     * @return a {@link Command}
     */
    static Command retrieveCommand(String name) {
        return CommandManager.retrieveCommand(name);
    }

    /**
     *Method for executing {@link Command} and retrieving response {@link Route}
     *
     * @param request {@link RequestContext} for getting and setting attributes
     * @param response {@link ResponseContext} for getting cookies
     * @return a {@link Route}
     */
    Route execute(RequestContext request, ResponseContext response);
}
