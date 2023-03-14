package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.IOrdenService;

public class OrdenServiceImple implements IOrdenService {

    private final OrdenTrabajoRepository repo;

    public OrdenServiceImple(OrdenTrabajoRepository repo) {
        this.repo = repo;
    }

    @Override
    public OrdenTrabajo altaOrden(OrdenTrabajo orden) {
        //orden.setFechaIngreso();
        return this.repo.save(orden);
    }

    @Override
    public OrdenTrabajo modiOrden(OrdenTrabajo orden) {
        return null;
    }
}
