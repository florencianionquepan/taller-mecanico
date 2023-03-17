package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface IOrdenService {
    OrdenTrabajo altaOrden(OrdenTrabajo orden);
    OrdenTrabajo iniciarReparacion(Long id);
    List<OrdenTrabajo> verTodas();
    OrdenTrabajo buscarById(Long id);
    void addManoObra(OrdenTrabajo orden, ManoObra obra);
}
