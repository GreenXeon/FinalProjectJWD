package by.epam.jwd.finalproj.command;

import javax.servlet.http.HttpServletResponse;

public class WrappingResponseContext implements ResponseContext{
    private final HttpServletResponse response;

    private WrappingResponseContext(HttpServletResponse response){
        this.response = response;
    }

    

    public static ResponseContext of(HttpServletResponse response){
        return new WrappingResponseContext(response);
    }
}
