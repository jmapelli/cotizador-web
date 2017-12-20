<%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 19/12/2017
  Time: 02:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>

<div class="content-wrapper">
    <div class="container-fluid">
        <form>
            <div class="form-group">
                <div class="form-row">
                    <div class="col-md-6">
                        <label for="exampleInputPassword1">Password</label>
                        <input class="form-control" id="exampleInputPassword1" type="password" placeholder="Password">
                    </div>
                    <div class="col-md-6">
                        <label for="exampleConfirmPassword">Confirm password</label>
                        <input class="form-control" id="exampleConfirmPassword" type="password"
                               placeholder="Confirm password">
                    </div>
                    <button class="btn btn-primary btn-block">Buscar</button>
                </div>
            </div>
        </form>

    </div>
</div>

<%@ include file="../common/footer.jsp" %>