<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionEntity" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 28/12/2017
  Time: 01:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String error_message = (String) request.getAttribute("error_message");
    CotizacionEntity cotizacion = (CotizacionEntity) request.getAttribute("cotizacion");
%>

<div class="modal fade" id="modal_result" tabindex="-1" role="dialog" aria-labelledby=""
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <% if (error_message != null) { %>
                <h4 class="modal-title" style="width: 100%; text-align: center;">Error al crear</h4>
                <% } else { %>
                <h4 class="modal-title" style="width: 100%; text-align: center;">Cotización creada</h4>
                <% } %>
            </div>
            <div class="modal-body" style="text-align: center">
                <% if (error_message != null) { %>
                <div class="alert alert-danger" style="margin-bottom: 0;">
                    <%=error_message%>
                </div>
                <% } else { %>
                La cotizacion <b><%=cotizacion.getNumero()%></b> ha sido creada.
                <% } %>
            </div>

            <div class="modal-footer">
                <% if (error_message != null) { %>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <% } else { %>
                <button id="descargar" type="button" class="btn btn-primary">Imprimir
                </button>
                <button id="volver" type="button" class="btn btn-default">Volver
                </button>
                <% } %>
            </div>

        </div>
    </div>
</div>

<script>
    $(function () {
        $('#modal_result').modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });

        $('#volver').click(function () {
            document.location.href = '<%=request.getContextPath()%>/';
        });
    });
</script>