package pe.lizard.cotizador.util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    public static String getAction(HttpServletRequest request) {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "";
        }

        return action;
    }
}
