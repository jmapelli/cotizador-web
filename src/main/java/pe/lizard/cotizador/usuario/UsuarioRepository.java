package pe.lizard.cotizador.usuario;

import pe.lizard.cotizador.db.Connection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.logging.Logger;

public class UsuarioRepository {

    private static final Logger LOG = Logger.getLogger(UsuarioRepository.class.getName());
    private EntityManager em;

    public UsuarioEntity findByUsuario(String usuario) {
        UsuarioEntity entity = null;

        try {
            em = Connection.getInstance();

            String sql = "select * from usuario where usuario like ? ";
            Query q = em.createNativeQuery(sql, UsuarioEntity.class);
            q.setParameter(1, usuario);

            entity = (UsuarioEntity) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

}
