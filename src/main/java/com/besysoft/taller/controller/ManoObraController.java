package com.besysoft.taller.controller;

import com.besysoft.taller.dto.ManoObraMecanicoDTO;
import com.besysoft.taller.dto.mapper.IManoObraMecanicoMapper;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.service.interfaces.IManoObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manoObras")
public class ManoObraController {

    private final IManoObraService service;
    private final IManoObraMecanicoMapper mapper;

    public ManoObraController(IManoObraService service, IManoObraMecanicoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @PostMapping
    public ResponseEntity<?> creacionManoObra(@RequestBody @Valid ManoObraMecanicoDTO dto){
        ManoObra creada=this.service.altaManoObra(this.mapper.mapToEntity(dto));
        ManoObraMecanicoDTO creadaResp=this.mapper.mapToDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(creadaResp);
    }

    @PutMapping("/{id}")
    //Mecanico asignado completa detalle y tiempos en mano de obra asignada
    //Una vez finalizada la reparacion
    public ResponseEntity<?> modiManoObra(@PathVariable Long id,
                                           @RequestBody ManoObraMecanicoDTO dto){
        ManoObra manoObra=this.mapper.mapToEntity(dto);
        ManoObra actual=this.service.modiManoObra(manoObra,id);
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",this.mapper.mapToDto(actual));
        return ResponseEntity.ok(mensajeBody);
    }
}
