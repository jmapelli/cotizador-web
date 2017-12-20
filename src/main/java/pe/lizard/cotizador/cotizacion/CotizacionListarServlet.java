package pe.lizard.cotizador.cotizacion;

import pe.lizard.cotizador.util.ErrorUtil;
import pe.lizard.cotizador.util.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CotizacionListarServlet", urlPatterns = "/cotizacion/listar")
public class CotizacionListarServlet extends HttpServlet {

    public static final String FINDBYCLIENTE = "cliente";
    public static final String FINDBYSOLICITANTE = "solicitante";
    public CotizacionService cotizacionService = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ServletUtil.getAction(request);

        switch (action) {
            case FINDBYCLIENTE:
                this.doGetFindByCliente(request, response);
                break;
            case FINDBYSOLICITANTE:
                this.doGetFindBySolicitante(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void doGetFindByCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String cliente = request.getParameter("valor");
            List<CotizacionEntity> cotizaciones = cotizacionService.findByCliente(cliente);
            request.setAttribute("cotizaciones", cotizaciones);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/listar_result.jsp");
        rd.forward(request, response);
    }

    private void doGetFindBySolicitante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String solicitante = request.getParameter("valor");
            List<CotizacionEntity> cotizaciones = cotizacionService.findBySolicitante(solicitante);
            request.setAttribute("cotizaciones", cotizaciones);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/listar_result.jsp");
        rd.forward(request, response);
    }

}
