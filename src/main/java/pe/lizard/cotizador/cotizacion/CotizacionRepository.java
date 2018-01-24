package pe.lizard.cotizador.cotizacion;

import pe.lizard.cotizador.db.Connection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    public List<CotizacionEntity> findByCliente(String cliente) {
        List<CotizacionEntity> cotizaciones = null;

        try {
            em = Connection.getInstance();

            String sql = "select * from cotizacion where cliente like ?";
            Query q = em.createNativeQuery(sql, CotizacionEntity.class);
            q.setParameter(1, "%" + cliente + "%");

            cotizaciones = (List<CotizacionEntity>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cotizaciones;
    }

    public List<CotizacionEntity> findBySolicitante(String solicitante) {
        List<CotizacionEntity> cotizaciones = null;

        try {
            em = Connection.getInstance();

            String sql = "select * from cotizacion where solicitante like ?";
            Query q = em.createNativeQuery(sql, CotizacionEntity.class);
            q.setParameter(1, "%" + solicitante + "%");

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
