package com.besysoft.taller.controller;

import com.besysoft.taller.dto.VehiculoDTO;
import com.besysoft.taller.dto.mapper.IVehiculoClienteMapper;
import com.besysoft.taller.dto.mapper.IVehiculoMapper;
import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final IVehiculoService service;
    private final IVehiculoMapper mapper;
    private final IVehiculoClienteMapper vehCliMap;

    public VehiculoController(IVehiculoService service,
                              IVehiculoMapper mapper,
                              IVehiculoClienteMapper vehCliMap) {
        this.service = service;
        this.mapper = mapper;
        this.vehCliMap = vehCliMap;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @PostMapping
    public ResponseEntity<?> altaVehiculo(@RequestBody @Valid VehiculoDTO dto){
        Vehiculo veNuevo=this.mapper.mapToEntity(dto);
        Vehiculo veGu=this.service.altaVehiculo(veNuevo);
        VehiculoDTO veResp=this.mapper.mapToDto(veGu);
        return ResponseEntity.status(HttpStatus.CREATED).body(veResp);
    }

    @GetMapping("/{patente}")
    public ResponseEntity<?> buscarVehiculoPorPatente(@PathVariable String patente){
        Vehiculo vehPatente=this.service.buscarPorPatente(patente);
        VehiculoDTO resp=this.vehCliMap.mapToDto(vehPatente);
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",resp);
        return ResponseEntity.ok(mensajeBody);
    }
}
