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

@WebServlet(name = "CotizacionEditarServlet", urlPatterns = "/cotizacion/editar")
public class CotizacionEditarServlet extends HttpServlet {

    public static final String ACTION_AGREGAR_ITEM = "action_agregar_item";
    public static final String ACTION_LISTAR_ITEM = "action_listar_item";
    public static final String ACTION_ELIMINAR_ITEM = "action_eliminar_item";

    public CotizacionService cotizacionService = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ServletUtil.getAction(request);

        switch (action) {
            case ACTION_AGREGAR_ITEM:
                this.doPostAgregarItem(request, response);
                break;
            case ACTION_ELIMINAR_ITEM:
                this.doPostEliminarItem(request, response);
                break;
            default:
                this.doPostDefault(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ServletUtil.getAction(request);

        switch (action) {
            case ACTION_LISTAR_ITEM:
                this.doGetListarItem(request, response);
                break;
            default:
                this.doGetDefault(request, response);
                break;
        }
    }

    private void doPostDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        Long id = Long.parseLong(request.getParameter("id"));
        Double subtotal = Double.parseDouble(request.getParameter("subtotal"));
        Double igv = Double.parseDouble(request.getParameter("igv"));
        Double total = Double.parseDouble(request.getParameter("total"));
        List<CotizacionDetalleEntity> items = (List) request.getSession().getAttribute("items");

        try {
            CotizacionEntity cotizacion = cotizacionService.editar(id, subtotal, igv, total, items);

            request.setAttribute("cotizacion", cotizacion);
            request.getSession().removeAttribute("items");
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/editar_result.jsp");
        rd.forward(request, response);
    }

    private void doPostAgregarItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        Double cantidad = Double.parseDouble(request.getParameter("cantidad"));
        String descripcion = request.getParameter("descripcion");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        List<CotizacionDetalleEntity> items = (List) request.getSession().getAttribute("items");

        try {
            items = cotizacionService.agregarItem(cantidad, descripcion, precio, items);
            request.getSession().setAttribute("items", items);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/agregar_items_message.jsp");
        rd.forward(request, response);
    }

    private void doPostEliminarItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        Integer index = Integer.parseInt(request.getParameter("index"));
        List<CotizacionDetalleEntity> items = (List) request.getSession().getAttribute("items");

        try {
            items = cotizacionService.eliminarItem(index, items);
            request.getSession().setAttribute("items", items);
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/agregar_items_listar.jsp");
        rd.forward(request, response);
    }

    private void doGetDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            Long idCotizacion = Long.parseLong(request.getParameter("id"));

            CotizacionEntity cotizacion = cotizacionService.findById(idCotizacion);
            List<CotizacionDetalleEntity> items = cotizacionService.getItems(idCotizacion);

            request.setAttribute("cotizacion", cotizacion);
            request.getSession().setAttribute("items", items);

        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/editar.jsp");
        rd.forward(request, response);
    }

    private void doGetListarItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/template/cotizacion/agregar_items_listar.jsp");
        rd.forward(request, response);
    }
}
