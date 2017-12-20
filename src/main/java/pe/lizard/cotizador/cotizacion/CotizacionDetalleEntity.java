package pe.lizard.cotizador.cotizacion;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Lombok
@Data
@NoArgsConstructor

// Jpa
@Entity
@Table(name = "cotizacion_detalle")
public class CotizacionDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Double importe;

}
