package by.epam.jwd.finalproj.controller;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.WrappingRequestContext;
import by.epam.jwd.finalproj.dao.impl.UserDao;
import by.epam.jwd.finalproj.model.User;

import java.io.*;
import java.util.List;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", value = "/controller")
public class HelloServlet extends HttpServlet {

    private static final String COMMAND_PARAMETER_NAME = "command";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public void destroy() {
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.getWriter().println("hello, nigger " + req.getParameter("email") + req.getParameter("command"));
        final String commandName = req.getParameter(COMMAND_PARAMETER_NAME);
        final Command businessCommand = Command.retrieveCommand(commandName);
        final ResponseContext result = businessCommand.execute(WrappingRequestContext.of(req));
        UserDao userDao = new UserDao();
        Optional<List<User>> users = userDao.findAll();

        for(User user : users.get()){
            resp.getWriter().println(user.getLogin());
        }

        if (result.isRedirect()) {
            //todo
        } else {
            final RequestDispatcher dispatcher = req.getRequestDispatcher(result.getPage());
            dispatcher.forward(req, resp);
        }
    }
}