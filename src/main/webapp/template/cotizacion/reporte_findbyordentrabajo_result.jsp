<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionDetalleEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionEntity" %>
<%@ page import="pe.lizard.cotizador.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/04/2018
  Time: 08:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    CotizacionDetalleEntity detalle = (CotizacionDetalleEntity) request.getAttribute("detalle");
%>

<% if (detalle != null) { %>
<div class="form-group">
    <div class="form-row">
        <div class="col-md-3">
            <label>Fecha:</label>
            <input type="text" class="form-control"
                   value="<%=DateUtil.toString("dd-MM-yyyy",detalle.getCotizacion().getFecha())%>" readonly/>
        </div>
        <div class="col-md-3">
            <label>Solicitado por:</label>
            <input type="text" class="form-control"
                   value="<%=detalle.getCotizacion().getSolicitante()%>" readonly/>
        </div>
        <div class="col-md-3">
            <label>Sucursal:</label>
            <input type="text" class="form-control" value="<%=detalle.getCotizacion().getSucursal()%>" readonly/>
        </div>
        <div class="col-md-3">
            <label>Cliente:</label>
            <input type="text" class="form-control" value="<%=detalle.getCotizacion().getCliente()%>" readonly/>
        </div>
    </div>
</div>

<hr>


<div class="table-responsive">
    <div id="dataTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
        <div class="row">
            <div class="col-sm-12" style="padding: 0;">
                <table class="table table-bordered" cellspacing="0" style="width: 100%;">
                    <thead>
                    <tr>
                        <th>Orden trabajo</th>
                        <th>Cliente</th>
                        <th>Cantidad</th>
                        <th>Descripción</th>
                        <th>Precio uni.</th>
                        <th>Importe</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (detalle.getEstado() == 1) { %>
                    <tr>
                        <td><%=detalle.getNroOrdenTrabajo()%>
                        </td>
                        <td><%=detalle.getCotizacion().getCliente()%>
                        </td>
                        <td><%=detalle.getCantidad()%>
                        </td>
                        <td><%=detalle.getDescripcion()%>
                        </td>
                        <td><%=detalle.getPrecio()%>
                        </td>
                        <td class="importe"><%=detalle.getImporte()%>
                        </td>
                    </tr>
                    <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<% } else {%>
<p style="text-align: center;">
    No se han encontrado resultados.
</p>
<% } %>

