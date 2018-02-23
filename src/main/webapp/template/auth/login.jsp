<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 17/12/2017
  Time: 08:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String error_message = (String) request.getAttribute("error_message");
%>

<%@ include file="../common/header.jsp" %>
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Iniciar sesión</div>
        <div class="card-body">
            <form action="auth" method="post">
                <div class="form-group">
                    <label for="usuario">Usuario</label>
                    <input id="usuario" name="usuario" class="form-control" type="text">
                </div>

                <div class="form-group">
                    <label for="clave">Contraseña</label>
                    <input id="clave" name="clave" class="form-control" type="password">
                </div>

                <button class="btn btn-primary btn-block">Iniciar sesión</button>
            </form>

            <%
                if (error_message != null && !error_message.isEmpty()) {
            %>
            <br>
            <div class="alert alert-danger alert-dismissable" style="margin-bottom: 0;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <%=error_message%>
            </div>
            <%
                }
            %>

        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>