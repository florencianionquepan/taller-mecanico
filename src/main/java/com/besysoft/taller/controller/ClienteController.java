package com.besysoft.taller.controller;

import com.besysoft.taller.dto.ClienteDTO;
import com.besysoft.taller.dto.ClienteRecibidoDTO;
import com.besysoft.taller.dto.mapper.IClienteMapper;
import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.service.interfaces.IClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService service;
    private final IClienteMapper mapper;

    public ClienteController(IClienteService service, IClienteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    private ResponseEntity<?> successResponse(Cliente cliente){
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",cliente);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/vehiculo")
    public ResponseEntity<?> recibirVehiculoCliente(@RequestParam String email, String patente){
        Cliente ent=this.service.recibeCliente(email, patente);
        return this.successResponse(ent);
    }

    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody @Valid ClienteDTO dto){
        Cliente entity=this.mapper.mapToEntity(dto);
        Cliente nuevo=this.service.altaCliente(entity);
        ClienteDTO resp=this.mapper.mapToDto(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
