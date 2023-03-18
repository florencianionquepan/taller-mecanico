package com.besysoft.taller.controller;

import com.besysoft.taller.dto.ManoObraMecanicoOrdenDTO;
import com.besysoft.taller.dto.mapper.IManoObraMecanicoOrdenMapper;
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
    private final IManoObraMecanicoOrdenMapper mapper;

    public ManoObraController(IManoObraService service, IManoObraMecanicoOrdenMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @PostMapping
    //El mecanico crea la mano de obra asignada a la orden de trabajo
    public ResponseEntity<?> creacionManoObra(@RequestBody @Valid ManoObraMecanicoOrdenDTO dto){
        ManoObra creada=this.service.altaManoObra(this.mapper.mapToEntity(dto));
        ManoObraMecanicoOrdenDTO creadaResp=this.mapper.mapToDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(creadaResp);
    }
}
