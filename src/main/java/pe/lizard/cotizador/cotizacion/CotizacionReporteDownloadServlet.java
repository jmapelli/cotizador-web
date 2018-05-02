package pe.lizard.cotizador.cotizacion;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pe.lizard.cotizador.util.DateUtil;
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

@WebServlet(name = "CotizacionReporteDownloadServlet", urlPatterns = "/cotizacion/reporte/download")
public class CotizacionReporteDownloadServlet extends HttpServlet {

    public static final String DOWNLOADBYCOTIZACION = "cotizacion";
    public static final String DOWNLOADBYCLIENTE = "cliente";
    public static final String DOWNLOADBYSOLICITADO = "solicitado";
    public static final String DOWNLOADBYORDENTRABAJO = "ordentrabajo";
    public CotizacionService cotizacionService = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ServletUtil.getAction(request);

        switch (action) {
            case DOWNLOADBYCOTIZACION:
                this.doGetDownloadByCotizacion(request, response);
                break;
            case DOWNLOADBYCLIENTE:
                this.doGetDownloadByCliente(request, response);
                break;
            case DOWNLOADBYSOLICITADO:
                this.doGetDownloadBySolicitado(request, response);
                break;
            case DOWNLOADBYORDENTRABAJO:
                this.doGetDownloadByOrdenTrabajo(request, response);
                break;
        }
    }

    private void doGetDownloadByCotizacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String nroCotizacion = request.getParameter("valor");

            List<CotizacionDetalleEntity> detalle = cotizacionService.findByNroCotizacion(nroCotizacion);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(0, "Hoja1");

            HSSFRow rowLbl = sheet.createRow(1);
            HSSFCell lblSolicitado = rowLbl.createCell(1);
            lblSolicitado.setCellValue("Solicitado por:");

            HSSFRow rowVal = sheet.createRow(2);
            HSSFCell valSolicitado = rowVal.createCell(1);
            valSolicitado.setCellValue(detalle.get(0).getCotizacion().getSolicitante());

            String[] headers = new String[]{"Orden trabajo", "Cantidad", "Descripción", "Precio uni.", "Importe"};

            HSSFRow headerRow = sheet.createRow(4);
            for (int i = 0; i < headers.length; ++i) {
                HSSFCell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i].toUpperCase());
            }

            if (!detalle.isEmpty()) {
                int i = 5;
                for (CotizacionDetalleEntity item : detalle) {
                    HSSFRow dataRow = sheet.createRow(i++);
                    dataRow.createCell(1).setCellValue(item.getNroOrdenTrabajo());
                    dataRow.createCell(2).setCellValue(item.getCantidad());
                    dataRow.createCell(3).setCellValue(item.getDescripcion());
                    dataRow.createCell(4).setCellValue(item.getPrecio());
                    dataRow.createCell(5).setCellValue(item.getImporte());
                }
            }


            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=cotizacion_" + nroCotizacion + ".xls");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }
    }

    private void doGetDownloadByCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String cliente = request.getParameter("valor");

            List<CotizacionDetalleEntity> detalle = cotizacionService.findByCliente(cliente);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(0, "Hoja1");

            String[] headers = new String[]{"Orden trabajo", "Cantidad", "Descripción", "Precio uni.", "Importe"};

            HSSFRow headerRow = sheet.createRow(1);
            for (int i = 0; i < headers.length; ++i) {
                HSSFCell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i].toUpperCase());
            }

            if (!detalle.isEmpty()) {
                int i = 2;
                for (CotizacionDetalleEntity item : detalle) {
                    HSSFRow dataRow = sheet.createRow(i++);
                    dataRow.createCell(1).setCellValue(item.getNroOrdenTrabajo());
                    dataRow.createCell(2).setCellValue(item.getCantidad());
                    dataRow.createCell(3).setCellValue(item.getDescripcion());
                    dataRow.createCell(4).setCellValue(item.getPrecio());
                    dataRow.createCell(5).setCellValue(item.getImporte());
                }
            }

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=cliente_" + cliente + ".xls");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }
    }

    private void doGetDownloadBySolicitado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String solicitante = request.getParameter("valor");

            List<CotizacionDetalleEntity> detalle = cotizacionService.findBySolicitante(solicitante);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(0, "Hoja1");

            String[] headers = new String[]{"Orden trabajo", "Cliente", "Cantidad", "Descripción", "Precio uni.", "Importe"};

            HSSFRow headerRow = sheet.createRow(1);
            for (int i = 0; i < headers.length; ++i) {
                HSSFCell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i].toUpperCase());
            }

            if (!detalle.isEmpty()) {
                int i = 2;
                for (CotizacionDetalleEntity item : detalle) {
                    HSSFRow dataRow = sheet.createRow(i++);
                    dataRow.createCell(1).setCellValue(item.getNroOrdenTrabajo());
                    dataRow.createCell(2).setCellValue(item.getCotizacion().getCliente());
                    dataRow.createCell(3).setCellValue(item.getCantidad());
                    dataRow.createCell(4).setCellValue(item.getDescripcion());
                    dataRow.createCell(5).setCellValue(item.getPrecio());
                    dataRow.createCell(6).setCellValue(item.getImporte());
                }
            }

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=solicitante_" + solicitante + ".xls");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }
    }

    private void doGetDownloadByOrdenTrabajo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cotizacionService = new CotizacionService();

        try {
            String ordenTrabajo = request.getParameter("valor");

            List<CotizacionDetalleEntity> detalle = cotizacionService.findByOrdenTrabajo(ordenTrabajo);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(0, "Hoja1");

            HSSFRow rowLbl = sheet.createRow(1);
            HSSFCell lblFecha = rowLbl.createCell(1);
            lblFecha.setCellValue("Fecha:");

            HSSFCell lblSolicitado = rowLbl.createCell(2);
            lblSolicitado.setCellValue("Solicitado por:");

            HSSFCell lblSucursal = rowLbl.createCell(3);
            lblSucursal.setCellValue("Sucursal:");

            HSSFCell lblCliente = rowLbl.createCell(4);
            lblCliente.setCellValue("Cliente:");

            HSSFRow rowVal = sheet.createRow(2);
            HSSFCell valFecha = rowVal.createCell(1);
            valFecha.setCellValue(DateUtil.toString("dd-MM-yyyy", detalle.get(0).getCotizacion().getFecha()));

            HSSFCell valSolicitado = rowVal.createCell(2);
            valSolicitado.setCellValue(detalle.get(0).getCotizacion().getSolicitante());

            HSSFCell valSucursal = rowVal.createCell(3);
            valSucursal.setCellValue(detalle.get(0).getCotizacion().getSucursal());

            HSSFCell valCliente = rowVal.createCell(4);
            valCliente.setCellValue(detalle.get(0).getCotizacion().getCliente());

            String[] headers = new String[]{"Orden trabajo", "Cliente", "Cantidad", "Descripción", "Precio uni.", "Importe"};

            HSSFRow headerRow = sheet.createRow(4);
            for (int i = 0; i < headers.length; ++i) {
                HSSFCell cell = headerRow.createCell(i + 1);
                cell.setCellValue(headers[i].toUpperCase());
            }

            if (!detalle.isEmpty()) {
                int i = 5;
                for (CotizacionDetalleEntity item : detalle) {
                    HSSFRow dataRow = sheet.createRow(i++);
                    dataRow.createCell(1).setCellValue(item.getNroOrdenTrabajo());
                    dataRow.createCell(2).setCellValue(item.getCotizacion().getCliente());
                    dataRow.createCell(3).setCellValue(item.getCantidad());
                    dataRow.createCell(4).setCellValue(item.getDescripcion());
                    dataRow.createCell(5).setCellValue(item.getPrecio());
                    dataRow.createCell(6).setCellValue(item.getImporte());
                }
            }


            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=ordentrabajo_" + ordenTrabajo + ".xls");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            ErrorUtil.handler(request, e);
        }
    }
}
