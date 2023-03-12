package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.Cliente;

public interface IClienteService {

    Cliente altaCliente(Cliente cliente);
    boolean existeCliente(String email);
    Cliente recibeCliente(String correo, String patente);
}
