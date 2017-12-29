<%@ page import="pe.lizard.cotizador.auth.AuthLoginServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/12/2017
  Time: 01:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand">&nbsp;</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link"
                   href="<%=request.getContextPath()%>/auth?action=<%=AuthLoginServlet.ACTION_LOGOUT%>">
                    <i class="fa fa-fw fa-sign-out"></i>Cerrar sesión</a>
            </li>
        </ul>
    </div>
</nav>