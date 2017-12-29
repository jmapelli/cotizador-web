<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionDetalleEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 28/12/2017
  Time: 08:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error_message = (String) request.getAttribute("error_message");
%>

<%
    if (error_message != null) {
%>
<div class="alert alert-danger" style="margin-bottom: 0;">
    <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>--%>
    <%=error_message%>
</div>
<%
} else {
%>
<div class="alert alert-success" style="margin-bottom: 0;">
    <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>--%>
    El producto ha sido agregado
</div>
<%
    }
%>