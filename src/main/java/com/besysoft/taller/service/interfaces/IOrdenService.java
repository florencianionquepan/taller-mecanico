package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface IOrdenService {
    OrdenTrabajo altaOrden(OrdenTrabajo orden);
    OrdenTrabajo modiOrden(OrdenTrabajo orden);
    List<OrdenTrabajo> verTodas();
    OrdenTrabajo buscarById(Long id);
}
