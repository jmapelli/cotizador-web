<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.lizard.cotizador.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/12/2017
  Time: 03:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<%
    String error_message = (String) request.getAttribute("error_message");
    List<CotizacionEntity> cotizaciones = (List) request.getAttribute("cotizaciones");
%>

<%
    if (error_message != null) {
%>
<tr>
    <td colspan="7">
        <div class="alert alert-danger alert-dismissable" style="margin-bottom: 0;">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <%=error_message%>
        </div>
    </td>
</tr>
<%
} else if (cotizaciones.isEmpty()) {
%>
<tr>
    <td colspan="7" style="text-align: center;">
        No se han encontrado resultados.
    </td>
</tr>
<%
} else {
    for (CotizacionEntity cotizacion : cotizaciones) {
%>
<tr>
    <td><%=cotizacion.getSerie()%>
    </td>
    <td><%=cotizacion.getNumero()%>
    </td>
    <td><%=DateUtil.toString("dd/MM/yyyy", cotizacion.getFecha())%>
    </td>
    <td><%=cotizacion.getCliente()%>
    </td>
    <td><%=cotizacion.getSolicitante()%>
    </td>
    <td>s/. <%=cotizacion.getTotal()%>
    </td>
    <td>
        <a class="btn btn-success btn-xs" href="<%=request.getContextPath()+"/cotizacion/ver?id="+cotizacion.getId()%>"
           style="color:  #fff;">
            <i class="fa fa-eye"></i>
        </a>
    </td>
</tr>
<%
        }
    }
%>