package com.besysoft.taller.controller;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.service.interfaces.IManoObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manoObras")
public class ManoObraController {

    private final IManoObraService service;
    //queda pendiente el mapper

    public ManoObraController(IManoObraService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modiManoObra(@PathVariable Long id,
                                           @RequestBody ManoObra manoObra){
        ManoObra actual=this.service.modiManoObra(manoObra,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(actual);
    }
}
