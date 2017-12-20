package pe.lizard.cotizador.usuario;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioEntity login(String usuario, String clave) throws Exception {

        if (usuario == null || usuario.isEmpty()) {
            throw new Exception("El usuario es invalido");
        }

        if (clave == null || clave.isEmpty()) {
            throw new Exception("La clave es invalida");
        }

        UsuarioEntity usuarioEntity = this.findByUsuario(usuario);

        if (usuario == null) {
            throw new Exception("El usuario es incorrecto");
        }

        if (!clave.equals(usuarioEntity.getClave())) {
            throw new Exception("La contraseña es incorrecta");
        }

        return usuarioEntity;
    }

    public UsuarioEntity findByUsuario(String usuario) throws Exception {
        usuarioRepository = new UsuarioRepository();
        return usuarioRepository.findByUsuario(usuario);
    }

}
