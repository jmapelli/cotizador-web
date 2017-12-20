package pe.lizard.cotizador.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Lombok
@Data
@NoArgsConstructor

// Jpa
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String clave;

}
