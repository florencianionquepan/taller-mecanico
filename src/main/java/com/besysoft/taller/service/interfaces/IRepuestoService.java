package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.Repuesto;
import com.besysoft.taller.model.Vehiculo;

import java.util.List;

public interface IRepuestoService {
    Repuesto altaRepuesto(Repuesto repuesto);
    List<Repuesto> verRepuestos();
    Repuesto buscarById(Long id);
}
