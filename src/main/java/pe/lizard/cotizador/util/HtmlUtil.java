package pe.lizard.cotizador.util;

import javax.servlet.http.HttpServletRequest;

public class HtmlUtil {

    public static String getClassBody(HttpServletRequest request) {
        if (request.getRequestURI().contains("auth")) {
            return "bg-dark";
        } else {
            return "fixed-nav sticky-footer bg-dark";
        }
    }

}
