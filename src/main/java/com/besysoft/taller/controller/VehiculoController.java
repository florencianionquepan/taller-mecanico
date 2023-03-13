package com.besysoft.taller.controller;

import com.besysoft.taller.dto.VehiculoDTO;
import com.besysoft.taller.dto.mapper.IVehiculoMapper;
import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final IVehiculoService service;
    private final IVehiculoMapper mapper;

    public VehiculoController(IVehiculoService service, IVehiculoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> altaVehiculo(@RequestBody @Valid VehiculoDTO dto){
        Vehiculo veNuevo=this.mapper.mapToEntity(dto);
        Vehiculo veGu=this.service.altaVehiculo(veNuevo);
        VehiculoDTO veResp=this.mapper.mapToDto(veGu);
        return ResponseEntity.status(HttpStatus.CREATED).body(veResp);
    }
}
