package com.besysoft.taller.controller;

import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.dto.mapper.IOrdenTrabajoMapper;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.service.interfaces.IOrdenService;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordenes")
public class OrdenTrabajoController {

    private final IOrdenService service;
    private final IOrdenTrabajoMapper mapper;
    private Logger logger= LoggerFactory.getLogger(OrdenTrabajoController.class);

    public OrdenTrabajoController(IOrdenService service, IOrdenTrabajoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @PostMapping
    //Se genera la orden de trabajo
    public ResponseEntity<?> altaOrden(@RequestBody @Valid OrdenTrabajoDTO orden){
        OrdenTrabajo nueva=this.service.altaOrden(this.mapper.mapToEntity(orden));
        OrdenTrabajoDTO nuevaResp=this.mapper.mapToDto(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResp);
    }

    @PutMapping("/{id}/reparacion")
    //inicia reparacion
    public ResponseEntity<?> modiOrden(@PathVariable Long id){
        OrdenTrabajoDTO dtoResp=this.mapper.mapToDto(this.service.iniciarReparacion(id));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dtoResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping
    public ResponseEntity<?> verTodas(){
        List<OrdenTrabajoDTO> ordenes=this.mapper.mapListToDto(this.service.verTodas());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenes);
        return ResponseEntity.ok(mensajeBody);
    }
}
