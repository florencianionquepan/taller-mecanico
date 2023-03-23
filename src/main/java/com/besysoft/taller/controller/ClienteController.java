package com.besysoft.taller.controller;

import com.besysoft.taller.dto.ClienteDTO;
import com.besysoft.taller.dto.mapper.IClienteMapper;
import com.besysoft.taller.dto.mapper.IVehiculoClienteMapper;
import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.service.interfaces.IClienteService;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@Api(value="Cliente Controller", tags="Acciones permitidas para Cliente")
public class ClienteController {

    private final IClienteService service;
    private final IClienteMapper mapper;

    public ClienteController(IClienteService service, IClienteMapper mapper,
                             IVehiculoClienteMapper vehiClienteMapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    private ResponseEntity<?> successResponse(ClienteDTO dto){
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dto);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{email}/vehiculos/{patente}")
    public ResponseEntity<?> asociarVehiculoCliente(@PathVariable String email,
                                                    @PathVariable String patente){
        Cliente ent=this.service.recibeCliente(email, patente);
        ClienteDTO dto=this.mapper.mapToDto(ent);
        return this.successResponse(dto);
    }


    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody @Valid ClienteDTO dto){
        Cliente entity=this.mapper.mapToEntity(dto);
        Cliente nuevo=this.service.altaCliente(entity);
        ClienteDTO resp=this.mapper.mapToDto(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
