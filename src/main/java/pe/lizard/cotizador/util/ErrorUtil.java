package pe.lizard.cotizador.util;

import javax.servlet.http.HttpServletRequest;

public class ErrorUtil {

    public static void handler(HttpServletRequest request, Exception exception) {
        request.setAttribute("error_message", exception.getMessage());
    }

}
