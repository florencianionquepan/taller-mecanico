package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.repository.ClienteRepository;
import com.besysoft.taller.service.interfaces.IClienteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImple implements IClienteService {

    private final ClienteRepository repo;

    public ClienteServiceImple(ClienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public Cliente altaCliente(Cliente cliente) {
        if(this.existeCliente(cliente)){
            return null;
        }
        return this.repo.save(cliente);
    }

    @Override
    public boolean existeCliente(Cliente cliente) {
        boolean existe=false;
        Optional<Cliente> oCli=this.repo.buscarPorEmail(cliente.getCorreoElectronico());
        if(oCli.isPresent()){
            existe=true;
        }
        return existe;
    }

    @Override
    public Cliente modiCliente(Cliente cliente) {
        return null;
    }
}
