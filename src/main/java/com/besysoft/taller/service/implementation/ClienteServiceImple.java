package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.repository.ClienteRepository;
import com.besysoft.taller.service.interfaces.IClienteService;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImple implements IClienteService {

    private final ClienteRepository repo;
    private final IVehiculoService vehiService;

    public ClienteServiceImple(ClienteRepository repo, IVehiculoService vehiService) {
        this.repo = repo;
        this.vehiService = vehiService;
    }

    @Override
    public Cliente altaCliente(Cliente cliente) {
        if(this.existeCliente(cliente.getCorreoElectronico())){
            return null;
        }
        return this.repo.save(cliente);
    }

    @Override
    public boolean existeCliente(String email) {
        boolean existe=false;
        Optional<Cliente> oCli=this.repo.buscarPorEmail(email);
        if(oCli.isPresent()){
            existe=true;
        }
        return existe;
    }

    @Override
    public Cliente recibeCliente(Cliente cliente) {
        //con dto viene un auto pero la entidad tiene una lista
        Vehiculo veNuevo= cliente.getListaVehiculos().get(0);
        if(!this.vehiService.existeVehiculo(veNuevo)){
            if(!this.existeCliente(cliente.getCorreoElectronico())){
                throw new RuntimeException(
                        String.format("El vehiculo con patente %s no existe," +
                                veNuevo.getPatente(),
                                "Y el cliente con email %s no se encuentra registrado," +
                                cliente.getCorreoElectronico() +
                                "Debe crear ambos y volver a intentarlo")
                );
            }else{
                throw new RuntimeException(
                        String.format("El vehiculo con patente %s no existe," +
                                "por favor cree uno nuevo y vuelva a intentarlo", veNuevo.getPatente())
                );
            }
        }
        if(!this.existeCliente(cliente.getCorreoElectronico())){
            throw new RuntimeException(
                    String.format("El cliente con email %s no se encuentra registrado," +
                            cliente.getCorreoElectronico() +
                            "Debe crearlo y volver a intentarlo")
            );
        }
        //si existe cliente y vehiculo, vincularlos:
        Cliente clienteDatos=this.repo.buscarPorEmail(cliente.getCorreoElectronico()).get();
        Cliente clienteAct=this.addVehiculoCliente(clienteDatos,veNuevo);
        return this.repo.save(clienteAct);
    }

    }
}
