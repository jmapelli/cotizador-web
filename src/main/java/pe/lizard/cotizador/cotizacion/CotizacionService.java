package pe.lizard.cotizador.cotizacion;

import java.util.List;

public class CotizacionService {

    private CotizacionRepository cotizacionRepository;

    public List<CotizacionEntity> findByCliente(String cliente) throws Exception {
        cotizacionRepository = new CotizacionRepository();

        if (cliente == null || cliente.isEmpty()) {
            throw new Exception("El cliente es invalido");
        }

        return cotizacionRepository.findByCliente(cliente);
    }

    public List<CotizacionEntity> findBySolicitante(String solicitante) throws Exception {
        cotizacionRepository = new CotizacionRepository();

        if (solicitante == null || solicitante.isEmpty()) {
            throw new Exception("El solicitante es invalido");
        }

        return cotizacionRepository.findBySolicitante(solicitante);
    }

}
