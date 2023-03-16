package com.besysoft.taller.controller;

import com.besysoft.taller.dto.MecanicoDTO;
import com.besysoft.taller.dto.mapper.IMecanicoMapper;
import com.besysoft.taller.model.Mecanico;
import com.besysoft.taller.service.interfaces.IMecanicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mecanicos")
public class MecanicoController {

    private final IMecanicoService service;
    private final IMecanicoMapper mapper;

    public MecanicoController(IMecanicoService service, IMecanicoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @PostMapping
    public ResponseEntity<?> altaMecanico(@RequestBody @Valid MecanicoDTO dto){
        Mecanico nuevo=this.service.altaMecanico(this.mapper.mapToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.mapToDto(nuevo));
    }

    @GetMapping
    public ResponseEntity<?> verTodos(){
        List<MecanicoDTO> mecanicos=this.mapper.mapListToDto(this.service.verTodos());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",mecanicos);
        return ResponseEntity.ok(mensajeBody);
    }
}
