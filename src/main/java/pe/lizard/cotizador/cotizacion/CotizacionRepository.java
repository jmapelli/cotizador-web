package pe.lizard.cotizador.cotizacion;

import pe.lizard.cotizador.db.Connection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class CotizacionRepository {

    private static final Logger LOG = Logger.getLogger(CotizacionRepository.class.getName());
    private EntityManager em;

    public CotizacionEntity findById(Long idCotizacion) {
        CotizacionEntity cotizacion = null;

        try {
            em = Connection.getInstance();

            String sql = "select * from cotizacion where id = ?";
            Query q = em.createNativeQuery(sql, CotizacionEntity.class);
            q.setParameter(1, idCotizacion);

            cotizacion = (CotizacionEntity) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cotizacion;
    }

    public List<CotizacionDetalleEntity> findByNroCotizacion(String nroCotizacion) {
        List<CotizacionDetalleEntity> detalle = null;

        try {
            em = Connection.getInstance();

            String sql = "select cd.* from cotizacion_detalle cd " +
                    "inner join cotizacion c on c.id = cd.cotizacion " +
                    "where c.numero like ?";
            Query q = em.createNativeQuery(sql, CotizacionDetalleEntity.class);
            q.setParameter(1, nroCotizacion + "%");

            detalle = (List<CotizacionDetalleEntity>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detalle;
    }

    public List<CotizacionDetalleEntity> findByCliente(String cliente) {
        List<CotizacionDetalleEntity> detalle = null;

        try {
            em = Connection.getInstance();

            String sql = "select cd.* from cotizacion_detalle cd " +
                    "inner join cotizacion c on c.id = cd.cotizacion " +
                    "where c.cliente like ?";
            Query q = em.createNativeQuery(sql, CotizacionDetalleEntity.class);
            q.setParameter(1, cliente + "%");

            detalle = (List<CotizacionDetalleEntity>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detalle;
    }

    public List<CotizacionDetalleEntity> findBySolicitante(String solicitante) {
        List<CotizacionDetalleEntity> detalle = null;

        try {
            em = Connection.getInstance();

            String sql = "select cd.* from cotizacion_detalle cd " +
                    "inner join cotizacion c on c.id = cd.cotizacion " +
                    "where c.solicitante like ?";
            Query q = em.createNativeQuery(sql, CotizacionDetalleEntity.class);
            q.setParameter(1, solicitante + "%");

            detalle = (List<CotizacionDetalleEntity>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detalle;
    }

    public List<CotizacionDetalleEntity> findByOrdenTrabajo(String ordenTrabajo) {
        List<CotizacionDetalleEntity> detalle = null;

        try {
            em = Connection.getInstance();

            String sql = "select * from cotizacion_detalle where nroOrdenTrabajo like '" + ordenTrabajo + "%' ";
            Query q = em.createNativeQuery(sql, CotizacionDetalleEntity.class);
            //q.setParameter(1, ordenTrabajo);

            //detalle = (CotizacionDetalleEntity) q.getSingleResult();
			detalle = (List<CotizacionDetalleEntity>) q.getResultList();
			
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detalle;
    }

    public List<CotizacionEntity> findByFiltro(String numero_cotizacion, String fecha, String solicitante,
                                               String sucursal, String cliente, String orden_trabajo) {
        List<CotizacionEntity> cotizaciones = null;

        try {
            em = Connection.getInstance();

            String sql = "select c.* from cotizacion c " +
                    "inner join cotizacion_detalle cd on cd.cotizacion = c.id ";

            List<String> list = new ArrayList<>();

            if (!numero_cotizacion.isEmpty()) {
                list.add(" c.numero like '" + numero_cotizacion + "%' ");
            }

            if (!fecha.isEmpty()) {
                list.add(" c.fecha like '" + fecha + "' ");
            }

            if (!solicitante.isEmpty()) {
                list.add(" c.solicitante like '" + solicitante + "%' ");
            }

            if (!sucursal.isEmpty()) {
                list.add(" c.sucursal like '" + sucursal + "%' ");
            }

            if (!cliente.isEmpty()) {
                list.add(" c.cliente like '" + cliente + "%' ");
            }

            if (!orden_trabajo.isEmpty()) {
                list.add(" cd.NROORDENTRABAJO like '" + orden_trabajo + "%' and cd.estado = 1 ");
            }

            String where = "";
            if (!list.isEmpty()) {
                where = "where " + String.join(" and ", list);
            }

            sql += where + " group by c.id ";

            Query q = em.createNativeQuery(sql, CotizacionEntity.class);

            cotizaciones = (List<CotizacionEntity>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cotizaciones;
    }

    public List<CotizacionDetalleEntity> getItems(Long idCotizacion) {
        List<CotizacionDetalleEntity> items = null;

        try {
            em = Connection.getInstance();

            String sql = "select * from cotizacion_detalle where cotizacion = ?";
            Query q = em.createNativeQuery(sql, CotizacionDetalleEntity.class);
            q.setParameter(1, idCotizacion);

            items = (List<CotizacionDetalleEntity>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public CotizacionEntity guardar(CotizacionEntity cotizacion) {
        try {
            em = Connection.getInstance();

            em.getTransaction().begin();
            em.persist(cotizacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return cotizacion;
    }

    public CotizacionEntity editar(CotizacionEntity cotizacion) {
        CotizacionEntity entity = null;
        try {
            em = Connection.getInstance();

            entity = em.find(CotizacionEntity.class, cotizacion.getId());
            em.getTransaction().begin();

            entity.setSubtotal(cotizacion.getSubtotal());
            entity.setIgv(cotizacion.getIgv());
            entity.setTotal(cotizacion.getTotal());

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warning(e.getLocalizedMessage());
        }

        return entity;
    }

    public CotizacionDetalleEntity guardarItem(CotizacionDetalleEntity item) {
        try {
            em = Connection.getInstance();

            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOG.warning(e.getLocalizedMessage());
        }

        return item;
    }

    public CotizacionDetalleEntity editarItem(CotizacionDetalleEntity item) {
        CotizacionDetalleEntity entity = null;
        try {
            em = Connection.getInstance();

            entity = em.find(CotizacionDetalleEntity.class, item.getId());
            em.getTransaction().begin();
            entity.setEstado(item.getEstado());
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warning(e.getLocalizedMessage());
        }

        return entity;
    }
}
