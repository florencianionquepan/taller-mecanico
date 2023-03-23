package com.besysoft.taller.controller;

import com.besysoft.taller.dto.AdministrativoDTO;
import com.besysoft.taller.dto.RecepcionistaDTO;
import com.besysoft.taller.dto.mapper.IRecepMapper;
import com.besysoft.taller.model.Recepcionista;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import io.swagger.annotations.Api;
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
@RequestMapping("/recepcionistas")
@Api(value="Recepcionista Controller", tags="Acciones permitidas para Recepcionista")
public class RecepcionController {

    private final IRecepcionService service;
    private final IRecepMapper mapper;
    public Map<String,Object> mensajeBody= new HashMap<>();
    private Logger logger= LoggerFactory.getLogger(RecepcionController.class);

    public RecepcionController(IRecepcionService service, IRecepMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> altaRecep(@RequestBody @Valid RecepcionistaDTO dto){
        Recepcionista nueva=this.service.altaRecepcion(this.mapper.mapToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.mapToDto(nueva));
    }

    @GetMapping
    public ResponseEntity<?> verRecep(){
        List<RecepcionistaDTO> recep=this.mapper.mapListToDto(this.service.verTodas());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",recep);
        return ResponseEntity.ok(mensajeBody);
    }
}
