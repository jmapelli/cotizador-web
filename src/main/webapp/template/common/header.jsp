<%@ page import="pe.lizard.cotizador.util.HtmlUtil" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 17/12/2017
  Time: 08:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cotización</title>

    <link href="<%=request.getContextPath()%>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <%--<link href="<%=request.getContextPath()%>/assets/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">--%>
    <link href="<%=request.getContextPath()%>/assets/css/sb-admin.css" rel="stylesheet">
</head>
<body class="<%=HtmlUtil.getClassBody(request)%>" style="margin-bottom: 0;">