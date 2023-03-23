package com.besysoft.taller.controller;

import com.besysoft.taller.dto.RepuestoDTO;
import com.besysoft.taller.dto.mapper.IRepuestoMapper;
import com.besysoft.taller.model.Repuesto;
import com.besysoft.taller.service.interfaces.IRepuestoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repuestos")
@Api(value="Repuesto Controller", tags="Repuesto Controller")
public class RepuestoController {

    private final IRepuestoService service;
    private final IRepuestoMapper mapper;

    public RepuestoController(IRepuestoService service, IRepuestoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @GetMapping
    @ApiOperation(value="Permite listar todos los repuestos existentes.")
    public ResponseEntity<?> verTodos(){
        List<RepuestoDTO> repuestos=this.mapper.mapListToDto(this.service.verRepuestos());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",repuestos);
        return ResponseEntity.ok(mensajeBody);
    }

    @PostMapping
    @ApiOperation(value="Permite crear un nuevo repuesto.")
    public ResponseEntity<?> altaRepuesto(@RequestBody @Valid RepuestoDTO dto){
        Repuesto repuesto=this.mapper.mapToEntity(dto);
        Repuesto nuevo=this.service.altaRepuesto(repuesto);
        RepuestoDTO resp=this.mapper.mapToDto(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

}
