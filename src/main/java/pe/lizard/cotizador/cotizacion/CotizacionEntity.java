package pe.lizard.cotizador.cotizacion;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

// Lombok
@Data
@NoArgsConstructor

// Jpa
@Entity
@Table(name = "cotizacion")
public class CotizacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serie;

    @Column(nullable = false)
    private String numero;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    private String cliente;

    private String sucursal;

    private String solicitante;

    @Column(nullable = false)
    private Double igv;

    @Column(nullable = false)
    private Double total;

}
