package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.repository.VehiculoRepository;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiculoServiceImple implements IVehiculoService {

    private final VehiculoRepository repo;

    public VehiculoServiceImple(VehiculoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo vehiculo) {
        if(this.existeVehiculo(vehiculo)){
            return null;
        }
        return this.repo.save(vehiculo);
    }

    @Override
    public boolean existeVehiculo(Vehiculo vehiculo) {
        boolean existe=false;
        Optional<Vehiculo> oVehi=this.repo.buscarPorPatente(vehiculo.getPatente());
        if(oVehi.isPresent()){
            existe=true;
        }
        return existe;
    }
}
