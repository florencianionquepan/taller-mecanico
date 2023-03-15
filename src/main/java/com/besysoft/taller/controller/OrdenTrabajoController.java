package com.besysoft.taller.controller;

import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.dto.mapper.IOrdenTrabajoMapper;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.service.interfaces.IOrdenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

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

    @PostMapping
    public ResponseEntity<?> altaOrden(@RequestBody OrdenTrabajoDTO orden){
        OrdenTrabajo nueva=this.service.altaOrden(this.mapper.mapToEntity(orden));
        OrdenTrabajoDTO nuevaResp=this.mapper.mapToDto(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResp);
    }
}
