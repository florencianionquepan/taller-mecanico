package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
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
            throw new NonExistingException(
                    String.format("El cliente con email %s ya se encuentra registrado ",
                            cliente.getCorreoElectronico()
                    )
            );
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
    public Cliente recibeCliente(String email, String patente) {
        Cliente cliente=new Cliente();
        cliente.setCorreoElectronico(email);
        Vehiculo veNuevo=new Vehiculo();
        veNuevo.setPatente(patente);
        if(!this.vehiService.existeVehiculo(veNuevo)){
            if(!this.existeCliente(cliente.getCorreoElectronico())){
                throw new NonExistingException(
                        String.format("El vehiculo con patente %s no existe." +
                                        "El cliente con email %s no se encuentra registrado.",
                                patente , email)
                );
            }else{
                throw new NonExistingException(
                        String.format("El vehiculo con patente %s no existe ",
                                patente)
                );
            }
        }
        if(!this.existeCliente(cliente.getCorreoElectronico())){
            throw new NonExistingException(
                    String.format("El cliente con email %s no se encuentra registrado ",
                            email)
            );
        }
        //si existe cliente y vehiculo, vincularlos:
        Cliente clienteDatos=this.repo.buscarPorEmail(email).get();
        Vehiculo veDatos=this.vehiService.buscarPorPatente(patente);
        if(clienteDatos.getListaVehiculos().contains(veDatos)){
            throw new NonExistingException(
                    String.format("El cliente con email %s ya posee el vehiculo %s vinculado ",
                            email, patente
                    )
            );
        }
        Cliente clienteAct=this.addVehiculoCliente(clienteDatos,veDatos);
        //this.vehiService.addClienteVehiculo(veDatos,clienteAct);
        return this.repo.save(clienteAct);
    }

    private Cliente addVehiculoCliente(Cliente clienteDatos, Vehiculo veDatos){
        List<Vehiculo> vehiculos=clienteDatos.getListaVehiculos();
        vehiculos.add(veDatos);
        clienteDatos.setListaVehiculos(vehiculos);
        return clienteDatos;
    }
}
