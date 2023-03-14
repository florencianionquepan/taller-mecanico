package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.OrdenTrabajo;

public interface IOrdenService {
    OrdenTrabajo altaOrden(OrdenTrabajo orden);
    OrdenTrabajo modiOrden(OrdenTrabajo orden);
}
