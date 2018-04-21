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

@WebServlet(name = "CotizacionReporteServlet", urlPatterns = "/cotizacion/reporte")
public class CotizacionReporteServlet extends HttpServlet {

    public static final String FINDBYCOTIZACION = "cotizacion";
    public static final String FINDBYCLIENTE = "cliente";
    public static final String FINDBYSOLICITADO = "solicitado";
    public static final String FINDBYORDENTRABAJO = "ordentrabajo";
    public CotizacionService cotizacionService = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ServletUtil.getAction(request);

        switch (action) {
            case FINDBYCOTIZACION:
                this.doGetFindByCotizacion(request, response);
                break;
            case FINDBYCLIENTE:
                this.doGetFindByCliente(request, response);
                break;
            case FINDBYSOLICITADO:
                this.doGetFindBySolicitado(request, response);
                break;
            case FINDBYORDENTRABAJO:
                this.doGetFindByOrdenTrabajo(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGetDefault(request, response);
    }

    private void doGetDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/reporte.jsp");
        rd.forward(request, response);
    }

    private void doGetFindByCotizacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String nroCotizacion = request.getParameter("valor");

            List<CotizacionDetalleEntity> detalle = cotizacionService.findByNroCotizacion(nroCotizacion);
            request.setAttribute("detalle", detalle);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/reporte_findbycotizacion_result.jsp");
        rd.forward(request, response);
    }

    private void doGetFindByCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String cliente = request.getParameter("valor");

            List<CotizacionDetalleEntity> detalle = cotizacionService.findByCliente(cliente);
            request.setAttribute("detalle", detalle);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/reporte_findbycliente_result.jsp");
        rd.forward(request, response);
    }

    private void doGetFindBySolicitado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String solicitante = request.getParameter("valor");

            List<CotizacionDetalleEntity> detalle = cotizacionService.findBySolicitante(solicitante);
            request.setAttribute("detalle", detalle);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/reporte_findbysolicitado_result.jsp");
        rd.forward(request, response);
    }

    private void doGetFindByOrdenTrabajo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String ordenTrabajo = request.getParameter("valor");

            CotizacionDetalleEntity detalle = cotizacionService.findByOrdenTrabajo(ordenTrabajo);
            request.setAttribute("detalle", detalle);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/reporte_findbyordentrabajo_result.jsp");
        rd.forward(request, response);
    }
}
