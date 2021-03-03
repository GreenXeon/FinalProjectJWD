package by.epam.jwd.finalproj.command;

public interface Command {
    static Command retrieveCommand(String name) {
        return CommandManager.retrieveCommand(name);
    }

    ResponseContext execute(RequestContext request);
}
