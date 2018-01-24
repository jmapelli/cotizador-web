<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionDetalleEntity" %>
<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionEntity" %>
<%@ page import="pe.lizard.cotizador.util.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionEditarServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/12/2017
  Time: 05:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>

<%
    CotizacionEntity cotizacion = (CotizacionEntity) request.getAttribute("cotizacion");
    List<CotizacionDetalleEntity> items = (List) request.getSession().getAttribute("items");
%>

<div class="content-wrapper">
    <div class="container-fluid">
        <input id="id" type="hidden" class="form-control" value="<%=cotizacion.getId()%>" readonly
               disabled>

        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                </div>
                <div class="col-sm-4 col-md-2">
                    <label>Serie</label>
                    <input id="serie" type="text" class="form-control" value="<%=cotizacion.getSerie()%>" readonly
                           disabled>
                </div>
                <div class="col-sm-8 col-md-4">
                    <label>Número</label>
                    <input id="numero" type="text" class="form-control" value="<%=cotizacion.getNumero()%>" readonly
                           disabled>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                    <label>Fecha</label>
                    <input id="fecha" type="date" class="form-control"
                           value="<%=DateUtil.toString("yyyy-MM-dd", cotizacion.getFecha())%>" readonly disabled>
                </div>
                <div class="col-md-6">
                    <label>Solicitado por</label>
                    <input id="solicitante" type="text" class="form-control" value="<%=cotizacion.getSolicitante()%>"
                           readonly disabled>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                    <label>Sucursal</label>
                    <input id="sucursal" type="text" class="form-control" value="<%=cotizacion.getSucursal()%>" readonly
                           disabled>
                </div>
                <div class="col-md-6">
                    <label>Cliente</label>
                    <input id="cliente" type="text" class="form-control" value="<%=cotizacion.getCliente()%>" readonly
                           disabled>
                </div>
            </div>
        </div>

        <hr>

        <div class="col-sm-3 col-md-2" style="padding: 0;">
            <button id="agregarItem" class="btn btn-primary btn-block">Agregar</button>
        </div>

        <div class="table-responsive">
            <div id="dataTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
                <div class="row">
                    <div class="col-sm-12" style="padding: 0;">
                        <table class="table table-bordered" cellspacing="0" style="width: 100%;">
                            <%--<thead style="background-color: #343a40; color: white;">--%>
                            <thead>
                            <tr>
                                <th>Cantidad</th>
                                <th>Descripción</th>
                                <th>Precio uni.</th>
                                <th>Importe</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="listar_result">
                            <% for (CotizacionDetalleEntity item : items) { %>
                            <% if (item.getEstado() == 1) { %>
                            <tr>
                                <td><%=item.getCantidad()%>
                                </td>
                                <td><%=item.getDescripcion()%>
                                </td>
                                <td><%=item.getPrecio()%>
                                </td>
                                <td class="importe"><%=item.getImporte()%>
                                </td>
                                <td>
                                    <a class="btn btn-success btn-xs btnEliminar" data-index="<%=items.indexOf(item)%>"
                                       style="color:  #fff;">
                                        <i class="fa fa-trash"></i>
                                    </a>
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

        <hr>

        <div class="form-group">
            <div class="form-row">
                <div class="col-sm-10 col-md-10">
                </div>
                <div class="col-sm-2 col-md-2">
                    <label>Subtotal</label>
                    <input id="subtotal" type="number" step="0.01" class="form-control"
                           value="<%=cotizacion.getSubtotal()%>">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-sm-10 col-md-10">
                </div>
                <div class="col-sm-2 col-md-2">
                    <label>IGV (18%)</label>
                    <input id="igv" type="number" step="0.01" class="form-control" value="<%=cotizacion.getIgv()%>">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-sm-10 col-md-10">
                </div>
                <div class="col-sm-2 col-md-2">
                    <label>Total</label>
                    <input id="total" type="number" step="0.01" class="form-control" value="<%=cotizacion.getTotal()%>">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                </div>
                <div class="col-md-3">
                    <button id="guardar" class="btn btn-primary btn-block">Guardar</button>
                </div>
                <div class="col-md-3">
                    <button id="cancelar" class="btn btn-default btn-block">Cancelar</button>
                </div>
            </div>
        </div>

    </div>
</div>

<div id="editar_result"></div>

<%@ include file="../common/footer.jsp" %>


<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby=""
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Agregar producto</h4>
            </div>
            <div class="modal-body" style="">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-12">
                            <label>Cantidad</label>
                            <input id="cantidad" type="number" step="0.01" class="form-control">
                        </div>
                        <div class="col-md-12">
                            <label>Descripción</label>
                            <input id="descripcion" type="text" class="form-control">
                        </div>
                        <div class="col-md-12">
                            <label>Precio unitario</label>
                            <input id="precio" type="number" step="0.01" type="text" class="form-control">
                        </div>
                    </div>
                </div>
                <div id="agregar_item_result"></div>
            </div>
            <div class="modal-footer">
                <button id="guardarItem" type="button" class="btn btn-primary">Agregar</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('#agregarItem').click(function () {
            $('#agregar_item_result').html('');

            $('#modal').modal({
                backdrop: 'static',
                keyboard: false,
                show: true
            });
        });

        $('#guardarItem').click(function () {
            $.post('editar', {
                action: '<%=CotizacionEditarServlet.ACTION_AGREGAR_ITEM%>',
                cantidad: $('#cantidad').val(),
                descripcion: $('#descripcion').val(),
                precio: $('#precio').val()
            }, function (response) {

                $.get('editar', {
                    action: '<%=CotizacionEditarServlet.ACTION_LISTAR_ITEM%>'
                }, function (response) {
                    $('#listar_result').html(response);
                });

                $('#agregar_item_result').html(response);

                var success = $('.alert-success');
                if (success.length) {
                    $('#cantidad').val('');
                    $('#descripcion').val('');
                    $('#precio').val('');
                    $('#subtotal').val('0');
                    $('#igv').val('0');
                    $('#total').val('0');
                }

                setTimeout(function () {
                    $('#agregar_item_result').html('');
                }, 1000);
            });
        });

        $('.btnEliminar').click(function () {
            $.post('editar', {
                action: '<%=CotizacionEditarServlet.ACTION_ELIMINAR_ITEM%>',
                index: $(this).data('index')
            }, function (response) {
                $('#listar_result').html(response);
                $('#subtotal').val('0');
                $('#igv').val('0');
                $('#total').val('0');
            });
        });

        $('#guardar').click(function () {
            $.post('editar', {
                id: $('#id').val(),
                subtotal: $('#subtotal').val(),
                igv: $('#igv').val(),
                total: $('#total').val()
            }, function (response) {
                $('#editar_result').html(response);
            });
        });

        $('#cancelar').click(function () {
            document.location.href = '<%=request.getContextPath()%>/';
        });
    });
</script>