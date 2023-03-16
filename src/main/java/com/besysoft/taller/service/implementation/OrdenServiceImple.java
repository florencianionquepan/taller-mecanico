package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.IOrdenService;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrdenServiceImple implements IOrdenService {

    private final OrdenTrabajoRepository repo;
    private final IRecepcionService recepService;

    public OrdenServiceImple(OrdenTrabajoRepository repo, IRecepcionService recepService) {
        this.repo = repo;
        this.recepService = recepService;
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
        orden.setEstado("CREADA");
        Long datetime = System.currentTimeMillis();
        Timestamp fechaIn = new Timestamp(datetime);
        orden.setFechaIngreso(fechaIn);
        //la asignacion del mecanico se realiza seleccionando de un get y creando la mano de obra con ese mecanico
        //y esta orden
        return this.repo.save(orden);
    }

    @Override
    public OrdenTrabajo modiOrden(OrdenTrabajo orden) {
        return null;
    }

    @Override
    public List<OrdenTrabajo> verTodas() {
        return (List<OrdenTrabajo>) this.repo.findAll();
    }
}
