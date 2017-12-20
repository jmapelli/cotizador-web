package pe.lizard.cotizador.auth;

import pe.lizard.cotizador.usuario.UsuarioEntity;
import pe.lizard.cotizador.usuario.UsuarioService;
import pe.lizard.cotizador.util.ErrorUtil;
import pe.lizard.cotizador.util.ServletUtil;
import pe.lizard.cotizador.util.SessionUtil;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuthLoginServlet", urlPatterns = "/auth")
public class AuthLoginServlet extends HttpServlet {

    private UsuarioService usuarioService = null;

    public static final String ACTION_LOGOUT = "logout";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ServletUtil.getAction(request);

        switch (action) {
            case ACTION_LOGOUT:
                request.getSession().removeAttribute(SessionUtil.AUTHENTICATED);
                request.getSession().removeAttribute(SessionUtil.USUARIO);
                break;
            default:
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/auth/login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        usuarioService = new UsuarioService();

        String usuarioRequest = request.getParameter("usuario");
        String claveRequest = request.getParameter("clave");

        try {
            UsuarioEntity usuarioEntity = usuarioService.login(usuarioRequest, claveRequest);

            request.getSession().setAttribute(SessionUtil.AUTHENTICATED, true);
            request.getSession().setAttribute(SessionUtil.USUARIO, usuarioEntity);

            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
            RequestDispatcher rd = request.getRequestDispatcher("/template/auth/login.jsp");
            rd.forward(request, response);
        }
    }

}
