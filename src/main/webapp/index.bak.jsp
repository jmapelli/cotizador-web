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
                <div class="col-md-5">
                    <label>Buscar por</label>
                    <select id="action" class="form-control">
                        <option value="<%=CotizacionListarServlet.FINDBYCLIENTE%>">cliente</option>
                        <option value="<%=CotizacionListarServlet.FINDBYSOLICITANTE%>">solicitante
                        </option>
                    </select>
                </div>
                <div class="col-md-7">
                    <label>Valor de busqueda</label>
                    <div class="input-group">
                        <input id="valor" type="text" class="form-control">
                        <span class="input-group-btn">
                            <button id="buscar" class="btn btn-primary" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
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
                    action: $('#action').val(),
                    valor: $('#valor').val()
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