package pe.lizard.cotizador.auth;

import pe.lizard.cotizador.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        String path = request.getRequestURI();
        path = path.substring(request.getContextPath().length());
        Pattern pattern = Pattern.compile("/auth.*||/assets.*");
        Matcher match = pattern.matcher(path);

        if (request.getSession().getAttribute(SessionUtil.AUTHENTICATED) == null && !match.matches()) {
            response.sendRedirect(request.getContextPath() + "/auth");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
