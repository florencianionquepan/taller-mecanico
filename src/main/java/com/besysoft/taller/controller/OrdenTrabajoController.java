package com.besysoft.taller.controller;

import com.besysoft.taller.dto.OrdenNuevaDTO;
import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.dto.mapper.IOrdenNuevaMapper;
import com.besysoft.taller.dto.mapper.OrdenNuevaMapper;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.service.interfaces.IOrdenService;
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
@RequestMapping("/ordenes")
public class OrdenTrabajoController {

    private final IOrdenService service;
    private final IOrdenNuevaMapper nuevaMapper;
    private Logger logger= LoggerFactory.getLogger(OrdenTrabajoController.class);

    public OrdenTrabajoController(IOrdenService service, IOrdenNuevaMapper nuevaMapper) {
        this.service = service;
        this.nuevaMapper = nuevaMapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @PostMapping
    //Se genera la orden de trabajo
    public ResponseEntity<?> altaOrden(@RequestBody @Valid OrdenNuevaDTO orden){
        OrdenTrabajo nueva=this.service.altaOrden(this.nuevaMapper.mapToEntity(orden));
        OrdenNuevaDTO nuevaResp=this.nuevaMapper.mapToDto(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResp);
    }

    @PutMapping("/{id}/reparacion")
    //inicia reparacion
    public ResponseEntity<?> modiOrden(@PathVariable Long id){
        OrdenNuevaDTO dtoResp=this.nuevaMapper.mapToDto(this.service.iniciarReparacion(id));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dtoResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/aFacturar")
    //La orden va a venir con las mano de obras (existentes ya) con campos completos
    //y traera los detalles que necesite crear
    public ResponseEntity<?> finalizaReparacion(@PathVariable Long id,
                                                @RequestBody OrdenTrabajoDTO dto){
        OrdenTrabajo orden=this.mapper.mapToEntity(dto);
        OrdenTrabajoDTO dtoResp=this.mapper.mapToDto(this.service.finalizarReparacion(id,orden));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dtoResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping
    public ResponseEntity<?> verTodas(){
        List<OrdenNuevaDTO> ordenes=this.nuevaMapper.mapListToDto(this.service.verTodas());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenes);
        return ResponseEntity.ok(mensajeBody);
    }
}
