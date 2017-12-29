package pe.lizard.cotizador.cotizacion;

import pe.lizard.cotizador.util.DateUtil;

import java.util.Date;
import java.util.List;

public class CotizacionService {

    private CotizacionRepository cotizacionRepository;

    public CotizacionEntity guardar(String serie, String numero, String fecha, String solicitante,
                                    String sucursal, String cliente, Double subtotal, Double igv,
                                    Double total, List<CotizacionDetalleEntity> items) throws Exception {
        cotizacionRepository = new CotizacionRepository();

        if (serie == null || serie.isEmpty()) {
            throw new Exception("La serie es invalida");
        }

        if (numero == null || numero.isEmpty()) {
            throw new Exception("El numero es invalido");
        }

        if (fecha == null || fecha.isEmpty()) {
            throw new Exception("La fecha es invalido");
        }

        if (items == null || items.isEmpty()) {
            throw new Exception("La lista de producto es invalida");
        }

        if (subtotal == null || subtotal < 0.01) {
            throw new Exception("El subtotal es invalido");
        }

        if (igv == null || igv < 0.01) {
            throw new Exception("El igv es invalido");
        }

        if (total == null || total < 0.01) {
            throw new Exception("El total es invalido");
        }

        Date fecha_cotizacion = DateUtil.toDate("yyyy-MM-dd", fecha);

        CotizacionEntity cotizacion = new CotizacionEntity();
        cotizacion.setSerie(serie);
        cotizacion.setNumero(numero);
        cotizacion.setFecha(fecha_cotizacion);
        cotizacion.setSolicitante(solicitante);
        cotizacion.setSucursal(sucursal);
        cotizacion.setCliente(cliente);
        cotizacion.setSubtotal(subtotal);
        cotizacion.setIgv(igv);
        cotizacion.setTotal(total);

        cotizacion = cotizacionRepository.guardar(cotizacion);

        for (CotizacionDetalleEntity item : items) {
            item.setCotizacion(cotizacion);
            cotizacionRepository.guardarItem(item);
        }

        return cotizacion;
    }

    public List<CotizacionEntity> findByCliente(String cliente) throws Exception {
        cotizacionRepository = new CotizacionRepository();

        if (cliente == null || cliente.isEmpty()) {
            throw new Exception("El cliente es invalido");
        }

        return cotizacionRepository.findByCliente(cliente);
    }

    public List<CotizacionEntity> findBySolicitante(String solicitante) throws Exception {
        cotizacionRepository = new CotizacionRepository();

        if (solicitante == null || solicitante.isEmpty()) {
            throw new Exception("El solicitante es invalido");
        }

        return cotizacionRepository.findBySolicitante(solicitante);
    }

    public List<CotizacionDetalleEntity> agregarItem(Double cantidad, String descripcion,
                                                     Double precio, List<CotizacionDetalleEntity> items)
            throws Exception {
        if (cantidad == null || cantidad < 0.01) {
            throw new Exception("La cantidad es invalida");
        }

        if (descripcion == null || descripcion.isEmpty()) {
            throw new Exception("La descripcion es invalida");
        }

        if (precio == null || precio < 0.01) {
            throw new Exception("El precio es invalido");
        }

        CotizacionDetalleEntity item = new CotizacionDetalleEntity();
        item.setCantidad(cantidad);
        item.setDescripcion(descripcion);
        item.setPrecio(precio);
        item.setImporte(item.getCantidad() * item.getPrecio());

        items.add(item);

        return items;
    }

    public List<CotizacionDetalleEntity> eliminarItem(Integer index, List<CotizacionDetalleEntity> items) throws Exception {
        CotizacionDetalleEntity item = items.get(index);
        items.remove(item);

        return items;
    }

}
