package pe.lizard.cotizador.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public class Connection {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());
    private static EntityManager em;

    public static final String PERSISTENCE_UNIT_NAME_DEV = "pe.lizard.cotizador.db.pu.dev";

    private Connection() {
    }

    public static EntityManager getInstance() {
        return em;
    }

    public static void init() {
        try {
            LOG.info("########## Inicializando conexión ##########");

            em = Persistence
                    .createEntityManagerFactory(Connection.PERSISTENCE_UNIT_NAME_DEV)
                    .createEntityManager();

            LOG.info("########## Conexión establecida ##########");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            LOG.info("########## Cerrando conexión ##########");

            if (em != null && em.isOpen()) {
                em.close();
            }

            LOG.info("########## Conexión cerrada ##########");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
