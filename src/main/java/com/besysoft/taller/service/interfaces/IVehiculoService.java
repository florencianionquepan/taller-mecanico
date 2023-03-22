package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.model.Vehiculo;

public interface IVehiculoService {

    Vehiculo altaVehiculo(Vehiculo vehiculo);
    boolean existeVehiculo(String patente);
    Vehiculo buscarPorPatente(String patente);
    void addClienteVehiculo(Vehiculo vehiculo, Cliente cliente);
}
