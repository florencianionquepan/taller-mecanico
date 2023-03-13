package com.besysoft.taller.controller;

import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final IVehiculoService service;

    public VehiculoController(IVehiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> altaVehiculo(@RequestBody Vehiculo vehi){
        return null;
    }
}
