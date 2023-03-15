package com.besysoft.taller.controller;

import com.besysoft.taller.dto.AdministrativoDTO;
import com.besysoft.taller.dto.RecepcionistaDTO;
import com.besysoft.taller.dto.mapper.IRecepMapper;
import com.besysoft.taller.model.Recepcionista;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recepcionistas")
public class RecepcionController {

    private final IRecepcionService service;
    private final IRecepMapper mapper;
    public Map<String,Object> mensajeBody= new HashMap<>();

    public RecepcionController(IRecepcionService service, IRecepMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> altaRecep(RecepcionistaDTO dto){
        Recepcionista nueva=this.service.altaRecepcion(this.mapper.mapToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.mapToDto(nueva));
    }

    @GetMapping
    public ResponseEntity<?> verAdmin(){
        List<RecepcionistaDTO> recep=this.mapper.mapListToDto(this.service.verTodas());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",recep);
        return ResponseEntity.ok(mensajeBody);
    }
}
