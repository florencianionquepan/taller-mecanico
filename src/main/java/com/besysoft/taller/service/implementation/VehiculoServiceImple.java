package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.repository.VehiculoRepository;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImple implements IVehiculoService {

    private Logger logger= LoggerFactory.getLogger(VehiculoServiceImple.class);
    private final VehiculoRepository repo;

    public VehiculoServiceImple(VehiculoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo vehiculo) {
        Optional<Vehiculo> oVehi=this.repo.buscarPorPatente(vehiculo.getPatente());
        if(oVehi.isPresent()){
            throw new NonExistingException(
                    String.format("El vehiculo con patente %s ya existe ",
                            vehiculo.getPatente()
                    )
            );
        }
        return this.repo.save(vehiculo);
    }

    @Override
    public boolean existeVehiculo(Vehiculo vehiculo) {
        Vehiculo ve=this.buscarPorPatente(vehiculo.getPatente());
        return true;
    }

    @Override
    public Vehiculo buscarPorPatente(String pat){
        Optional<Vehiculo> oVehi=this.repo.buscarPorPatente(pat);
        if(oVehi.isEmpty()){
            throw new NonExistingException(
                    String.format("El vehiculo con patente %s no existe ",
                            pat
                    )
            );
        }
        return oVehi.get();
    }
    
    @Override
    public void addClienteVehiculo(Vehiculo vehiculo, Cliente cliente){
        List<Cliente> clientes=vehiculo.getListaClientes();
        clientes.add(cliente);
        vehiculo.setListaClientes(clientes);
        this.repo.save(vehiculo);
    }
}
