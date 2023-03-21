package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Mecanico;
import com.besysoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface IOrdenService {
    OrdenTrabajo altaOrden(OrdenTrabajo orden);
    OrdenTrabajo altaManoObra(Long id, Mecanico asignado);
    OrdenTrabajo iniciarReparacion(Long id);
    OrdenTrabajo finalizarReparacion(Long id, OrdenTrabajo orden);
    OrdenTrabajo facturarOrden(Long id, OrdenTrabajo orden);
    OrdenTrabajo cerrarOrden(Long id);
    List<OrdenTrabajo> verTodas();
    OrdenTrabajo buscarById(Long id);
    void addManoObra(OrdenTrabajo orden, ManoObra obra);
}
