package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.IOrdenService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrdenServiceImple implements IOrdenService {

    private final OrdenTrabajoRepository repo;

    public OrdenServiceImple(OrdenTrabajoRepository repo) {
        this.repo = repo;
    }

    @Override
    public OrdenTrabajo altaOrden(OrdenTrabajo orden) {
        orden.setEstado("CREADA");
        Long datetime = System.currentTimeMillis();
        Timestamp fechaIn = new Timestamp(datetime);
        orden.setFechaIngreso(fechaIn);
        return this.repo.save(orden);
    }

    @Override
    public OrdenTrabajo modiOrden(OrdenTrabajo orden) {
        return null;
    }
}
