<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionDetalleEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionEntity" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/04/2018
  Time: 08:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<CotizacionDetalleEntity> detalle = (List) request.getAttribute("detalle");
%>

<% if (detalle != null && !detalle.isEmpty()) { %>
<div class="form-group">
    <div class="form-row">
        <div class="col-md-3">
            <label>Solicitado por:</label>
            <input type="text" class="form-control"
                   value="<%=detalle.get(0).getCotizacion().getSolicitante()%>" readonly/>
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
                        <th>Cantidad</th>
                        <th>Descripción</th>
                        <th>Precio uni.</th>
                        <th>Importe</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (CotizacionDetalleEntity item : detalle) { %>
                    <% if (item.getEstado() == 1) { %>
                    <tr>
                        <td><%=item.getNroOrdenTrabajo()%>
                        </td>
                        <td><%=item.getCantidad()%>
                        </td>
                        <td><%=item.getDescripcion()%>
                        </td>
                        <td><%=item.getPrecio()%>
                        </td>
                        <td class="importe"><%=item.getImporte()%>
                        </td>
                    </tr>
                    <% }%>
                    <% } %>
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

