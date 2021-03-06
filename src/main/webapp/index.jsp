<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionListarServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 16/12/2017
  Time: 06:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="template/common/header.jsp" %>
<%@ include file="template/common/nav.jsp" %>

<div class="content-wrapper">
    <div class="container-fluid">
        <div class="form-group">
            <div class="form-row">
                <div class="col-md-3">
                    <button id="nuevo" class="btn btn-primary btn-block">Nueva cotización</button>
                </div>
            </div>

            <hr>

            <div class="form-row">
                <div class="col-md-4">
                    <label>Número cotización:</label>
                    <input id="numero_cotizacion" type="text" class="form-control">
                </div>
                <div class="col-md-4">
                    <label>Fecha:</label>
                    <input id="fecha" type="date" class="form-control">
                </div>
                <div class="col-md-4">
                    <label>Solicitante:</label>
                    <input id="solicitante" type="text" class="form-control">
                </div>
                <div class="col-md-4">
                    <label>Sucursal:</label>
                    <input id="sucursal" type="text" class="form-control">
                </div>
                <div class="col-md-4">
                    <label>Cliente:</label>
                    <input id="cliente" type="text" class="form-control">
                </div>
                <div class="col-md-4">
                    <label>Orden de trabajo:</label>
                    <input id="orden_trabajo" type="text" class="form-control">
                </div>
                <div class="col-md-3">
                </div>
                <div class="col-md-3">
                </div>
                <div class="col-md-3">
                </div>
                <div class="col-md-3">
                    <label></label>
                    <button id="buscar" class="btn btn-primary btn-block">Buscar</button>
                </div>
            </div>
        </div>

        <hr>

        <div class="table-responsive">
            <div id="dataTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
                <div class="row">
                    <div class="col-sm-12" style="padding: 0;">
                        <table class="table table-bordered" cellspacing="0" style="width: 100%;">
                            <%--<thead style="background-color: #343a40; color: white;">--%>
                            <thead>
                            <tr role="row">
                                <th>Número</th>
                                <th>Fecha</th>
                                <th>Cliente</th>
                                <th>Solicitante</th>
                                <th>Total</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="listar_result">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%@ include file="template/common/footer.jsp" %>

<script>
    $(function () {
        $('#buscar').click(function (e) {
            e.preventDefault();

            $.get('cotizacion/listar', {
                    action: '<%=CotizacionListarServlet.FINDBYFILTRO%>',
                    numero_cotizacion: $('#numero_cotizacion').val(),
                    fecha: $('#fecha').val(),
                    solicitante: $('#solicitante').val(),
                    sucursal: $('#sucursal').val(),
                    cliente: $('#cliente').val(),
                    orden_trabajo: $('#orden_trabajo').val()
                }, function (response) {
                    $('#listar_result').html(response);
                }
            );
            5
        });

        $('#nuevo').click(function (e) {
            e.preventDefault();

            document.location.href = '<%=request.getContextPath()%>/cotizacion/crear';
        });
    });
</script>