package com.besysoft.taller.controller;

import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.service.interfaces.IClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos_clientes")
public class ClienteController {

    private final IClienteService service;

    public ClienteController(IClienteService service) {
        this.service = service;
    }

    @GetMapping("/{cliente}")
    public ResponseEntity<?> recibirClienteVehiculo(@RequestParam Cliente cliente){
        //conversion del dto a entidad
        //llamamos a modificar cliente del servicio que vaya arrojando los exceptions a medida que
        //va verificando
        this.service.recibeCliente(cliente);
        return null;
    }
}
