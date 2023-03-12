package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.Vehiculo;

public interface IVehiculoService {

    Vehiculo altaVehiculo(Vehiculo vehiculo);
    boolean existeVehiculo(Vehiculo vehiculo);
}
