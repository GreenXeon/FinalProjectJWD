package by.epam.jwd.finalproj.command;

/**
 * Interface for realization of mapping and page routing
 * Used as a return type of {@link Command#execute(RequestContext, ResponseContext)} method
 * Routing is a result of overriding this method in every class implementing this interface
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface Route {
    /**
     * Method for defining and retrieving page for a specific command routing
     *
     * @return {@link String} page for forwarding
     */
    String getPage();

    /**
     * Defines if the page from method {@link this#getPage()} should be redirected
     *
     * @return {@link Boolean} result
     */
    boolean isRedirect();
}
