<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionDetalleEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.lizard.cotizador.cotizacion.CotizacionCrearServlet" %><%--
  Created by IntelliJ IDEA.
  User: JosueAngel
  Date: 28/12/2017
  Time: 09:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CotizacionDetalleEntity> items = (List) request.getSession().getAttribute("items");
%>

<% for (CotizacionDetalleEntity item : items) { %>
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
        <a class="btn btn-success btn-xs btnEliminar" data-index="<%=items.indexOf(item)%>" style="color:  #fff;">
            <i class="fa fa-trash"></i>
        </a>
    </td>
</tr>
<% } %>

<script>
    $(function () {
        $('.btnEliminar').click(function () {
            $.post('crear', {
                action: '<%=CotizacionCrearServlet.ACTION_ELIMINAR_ITEM%>',
                index: $(this).data('index')
            }, function (response) {
                $('#listar_result').html(response);
            });
        });
    });
</script>
