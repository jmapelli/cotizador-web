package pe.lizard.cotizador.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public class Connection {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());
    private static EntityManager em;

    public static final String PERSISTENCE_UNIT_NAME_LOCAL = "pe.lizard.cotizador.db.pu.local";
    public static final String PERSISTENCE_UNIT_NAME_DEV = "pe.lizard.cotizador.db.pu.dev";
    public static final String PERSISTENCE_UNIT_NAME_PROD = "pe.lizard.cotizador.db.pu.prod";

    private Connection() {
    }

    public static EntityManager getInstance() {
        return em;
    }

    public static void init() {
        try {
            LOG.info("########## Inicializando conexión ##########");

            String pu = System.getenv("TOMCAT_ENVIRONMENT");
            if (pu == null || pu.isEmpty()) {
                pu = "";
            }
            LOG.info("########## "+pu.toUpperCase()+" ENVIRONMENT ##########");

            switch (pu) {
                case "prod":
                    em = Persistence.createEntityManagerFactory(Connection.PERSISTENCE_UNIT_NAME_PROD).createEntityManager();
                    break;
                case "dev":
                    em = Persistence.createEntityManagerFactory(Connection.PERSISTENCE_UNIT_NAME_DEV).createEntityManager();
                    break;
                default:
                    em = Persistence.createEntityManagerFactory(Connection.PERSISTENCE_UNIT_NAME_LOCAL).createEntityManager();
                    break;
            }

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
