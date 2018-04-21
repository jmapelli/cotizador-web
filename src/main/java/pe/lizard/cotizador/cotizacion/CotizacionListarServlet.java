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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CotizacionListarServlet", urlPatterns = "/cotizacion/listar")
public class CotizacionListarServlet extends HttpServlet {

    public static final String FINDBYFILTRO = "filtro";
    public CotizacionService cotizacionService = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ServletUtil.getAction(request);

        switch (action) {
            case FINDBYFILTRO:
                this.doGetFindByFiltro(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void doGetFindByFiltro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String numero_cotizacion = request.getParameter("numero_cotizacion");
            String fecha = request.getParameter("fecha");
            String solicitante = request.getParameter("solicitante");
            String sucursal = request.getParameter("sucursal");
            String cliente = request.getParameter("cliente");
            String orden_trabajo = request.getParameter("orden_trabajo");


            List<CotizacionEntity> cotizaciones = cotizacionService.findByFiltro(numero_cotizacion, fecha, solicitante, sucursal, cliente, orden_trabajo);
            request.setAttribute("cotizaciones", cotizaciones);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/listar_result.jsp");
        rd.forward(request, response);
    }

}
