package by.epam.jwd.finalproj.controller;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.WrappingRequestContext;
import by.epam.jwd.finalproj.dao.impl.UserDao;
import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;

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
        resp.getWriter().println("hello, nigger " + req.getParameter("login") + " " + req.getParameter("command"));
        //final String commandName = req.getParameter(COMMAND_PARAMETER_NAME);
        //final Command businessCommand = Command.retrieveCommand(commandName);
        //final ResponseContext result = businessCommand.execute(WrappingRequestContext.of(req));
        UserDao userDao = new UserDao();
        Optional<List<User>> users = userDao.findAll();
        if (users.isPresent()){
            resp.getWriter().println("users are read from database");
            resp.getWriter().println(users.get().get(0).getLogin());
        }
        else {
            resp.getWriter().println("reading is failed");
        }
        /*UserService userService = new UserService();
        Optional<UserDto> user = userService.login(req.getParameter("login"), req.getParameter("password"));
        if (!user.isPresent()) {
            resp.getWriter().println("failed");
        }
        else {
            resp.getWriter().println("ok");
        }*/

        /*if (result.isRedirect()) {
            //todo
        } else {
            final RequestDispatcher dispatcher = req.getRequestDispatcher(result.getPage());
            dispatcher.forward(req, resp);
        }*/
    }
}