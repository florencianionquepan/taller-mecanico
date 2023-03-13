package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.model.Vehiculo;

public interface IVehiculoService {

    Vehiculo altaVehiculo(Vehiculo vehiculo);
    boolean existeVehiculo(Vehiculo vehiculo);
    public Vehiculo buscarPorPatente(String patente);
    void addClienteVehiculo(Vehiculo vehiculo, Cliente cliente);
}
