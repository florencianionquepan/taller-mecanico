package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.EstadoOrden;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.IOrdenService;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImple implements IOrdenService {

    private final OrdenTrabajoRepository repo;
    private final IRecepcionService recepService;
    private final IVehiculoService vehiService;

    public OrdenServiceImple(OrdenTrabajoRepository repo,
                             IRecepcionService recepService,
                             IVehiculoService vehiService) {
        this.repo = repo;
        this.recepService = recepService;
        this.vehiService = vehiService;
    }

    @Override
    public OrdenTrabajo altaOrden(OrdenTrabajo orden) {
        if(!this.recepService.existeRecepcionista(orden.getRecepcionista())){
            throw new NonExistingException(
                    String.format("La recepcionista %s no existe",
                            orden.getRecepcionista().getPersona().getNombres()
                    )
            );
        }
        Vehiculo vehi=this.vehiService.buscarPorPatente(orden.getVehiculo().getPatente());
        orden.setVehiculo(vehi);
        orden.setEstado(EstadoOrden.CREADA);
        Long datetime = System.currentTimeMillis();
        Timestamp fechaIn = new Timestamp(datetime);
        orden.setFechaIngreso(fechaIn);
        OrdenTrabajo nueva=this.repo.save(orden);
        return nueva;
    }

    @Override
    public OrdenTrabajo iniciarReparacion(Long id) {
        OrdenTrabajo orden=this.buscarById(id);
        orden.setEstado(EstadoOrden.REPARACION);
        return this.repo.save(orden);
    }

    @Override
    public List<OrdenTrabajo> verTodas() {
        return (List<OrdenTrabajo>) this.repo.findAll();
    }

    @Override
    public OrdenTrabajo buscarById(Long id) {
        Optional<OrdenTrabajo> oEstado=this.repo.findById(id);
        if(oEstado.isEmpty()){
            throw new NonExistingException(
                    String.format("La orden de trabajo %d no existe ",
                            id)
            );
        }
        return oEstado.get();
    }

    @Override
    public void addManoObra(OrdenTrabajo orden, ManoObra obra) {
        List<ManoObra> obras=orden.getListaManoObra();
        obras.add(obra);
        orden.setListaManoObra(obras);
    }
}
