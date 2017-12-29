package pe.lizard.cotizador.cotizacion;

import pe.lizard.cotizador.db.Connection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class CotizacionRepository {

    private static final Logger LOG = Logger.getLogger(CotizacionRepository.class.getName());
    private EntityManager em;

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
}
