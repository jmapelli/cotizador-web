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

<div class="content-wrapper">
    <div class="container-fluid">


        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                </div>
                <div class="col-sm-4 col-md-2">
                    <label>Serie</label>
                    <input type="text" class="form-control">
                </div>
                <div class="col-sm-8 col-md-4">
                    <label>Número</label>
                    <input type="text" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                    <label>Fecha</label>
                    <input type="text" class="form-control">
                </div>
                <div class="col-md-6">
                    <label>Solicitado por</label>
                    <input type="text" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                    <label>Sucursal</label>
                    <input type="text" class="form-control">
                </div>
                <div class="col-md-6">
                    <label>Cliente</label>
                    <input type="text" class="form-control">
                </div>
            </div>
        </div>

        <hr>

        <div class="col-sm-3 col-md-2" style="padding: 0;">
            <button id="agregar" class="btn btn-primary btn-block">Agregar</button>
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
                    <input type="text" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-sm-10 col-md-10">
                </div>
                <div class="col-sm-2 col-md-2">
                    <label>IGV (18%)</label>
                    <input type="text" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-sm-10 col-md-10">
                </div>
                <div class="col-sm-2 col-md-2">
                    <label>Total</label>
                    <input type="text" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="form-row">
                <div class="col-md-6">
                </div>
                <div class="col-md-3">
                    <button id="emitir" class="btn btn-primary btn-block">Emitir</button>
                </div>
                <div class="col-md-3">
                    <button id="cancelar" class="btn btn-default btn-block">Cancelar</button>
                </div>
            </div>
        </div>

    </div>
</div>

<%@ include file="../common/footer.jsp" %>
