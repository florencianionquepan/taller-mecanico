package com.besysoft.taller.controller;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.service.interfaces.IManoObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manoObras")
public class ManoObraController {

    private final IManoObraService service;
    //queda pendiente el mapper

    public ManoObraController(IManoObraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> nuevaManoObra(@RequestBody  ManoObra manoObra){
        ManoObra nueva=this.service.altaManoObra(manoObra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
}
