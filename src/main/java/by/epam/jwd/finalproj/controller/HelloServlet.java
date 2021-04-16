package by.epam.jwd.finalproj.controller;

import by.epam.jwd.finalproj.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", value = "/controller")
public class HelloServlet extends HttpServlet {

    private static final String COMMAND_PARAMETER_NAME = "command";

    private final Logger logger = LogManager.getLogger(HelloServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public void destroy() {
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String commandName = req.getParameter(COMMAND_PARAMETER_NAME);
        final Command businessCommand = Command.retrieveCommand(commandName);
        final Route result = businessCommand.execute(WrappingRequestContext.of(req), WrappingResponseContext.of(resp));
        if (result.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + "/controller?command=" + result.getPage());
        } else {
            final RequestDispatcher dispatcher = req.getRequestDispatcher(result.getPage());
            dispatcher.forward(req, resp);
        }
    }
}