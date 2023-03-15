package com.besysoft.taller.controller;

import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.dto.mapper.IOrdenTrabajoMapper;
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
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        System.out.println("Current Time Stamp: "+timestamp);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.mapToEntity(orden));
    }
}
