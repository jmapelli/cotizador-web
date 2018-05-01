<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionReporteServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 20/04/2018
  Time: 12:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>

<div class="content-wrapper">
    <div class="container-fluid">
        <div class="form-group">
            <div class="form-row">
                <div class="col-md-3">
                    <label>Buscar por:</label>
                    <select id="action" class="form-control">
                        <option value="<%=CotizacionReporteServlet.FINDBYCOTIZACION%>">Cotización</option>
                        <option value="<%=CotizacionReporteServlet.FINDBYCLIENTE%>">Cliente</option>
                        <option value="<%=CotizacionReporteServlet.FINDBYSOLICITADO%>">Solicitante</option>
                        <option value="<%=CotizacionReporteServlet.FINDBYORDENTRABAJO%>">Orden de trabajo</option>
                    </select>
                </div>
                <div class="col-md-5">
                    <label>Valor:</label>
                    <input id="valor" type="text" class="form-control">
                </div>
                <div class="col-md-2">
                    <label>&nbsp;&nbsp;</label>
                    <button id="buscar" class="btn btn-primary btn-block">Buscar</button>
                </div>
                <div class="col-md-2">
                    <label>&nbsp;&nbsp;</label>
                    <button id="download" class="btn btn-primary btn-block">Download</button>
                </div>
            </div>
        </div>

        <hr>

        <div id="result"></div>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>
<script>
    $(function () {
        $('#buscar').click(function (e) {
            e.preventDefault();

            $.get('reporte', {
                    action: $('#action').val(),
                    valor: $('#valor').val()
                }, function (response) {
                    $('#result').html(response);
                }
            );
        });

        $('#download').click(function (e) {
            e.preventDefault();
            window.location = 'reporte/download?action=' + $('#action').val() + '&valor=' + $('#valor').val();
        });
    });
</script>