package com.besysoft.taller.controller;

import com.besysoft.taller.dto.*;
import com.besysoft.taller.dto.mapper.*;
import com.besysoft.taller.model.ManoObra;
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
    private final IOrdenDetalladaMapper detalladaMapper;
    private final IOrdenFacturadaMapper factuMapper;
    private final IOrdenTrabajoRespMapper ordenRespMapper;
    private final IManoObraMecanicoMapper manoObraMapper;
    private Logger logger= LoggerFactory.getLogger(OrdenTrabajoController.class);

    public OrdenTrabajoController(IOrdenService service,
                                  IOrdenNuevaMapper nuevaMapper,
                                  IOrdenDetalladaMapper detalladaMapper,
                                  IOrdenFacturadaMapper factuMapper,
                                  IOrdenTrabajoRespMapper ordenRespMapper,
                                  IManoObraMecanicoMapper manoObraMapper) {
        this.service = service;
        this.nuevaMapper = nuevaMapper;
        this.detalladaMapper = detalladaMapper;
        this.factuMapper = factuMapper;
        this.ordenRespMapper = ordenRespMapper;
        this.manoObraMapper = manoObraMapper;
    }

    public Map<String,Object> mensajeBody= new HashMap<>();

    @PostMapping
    //Se genera la orden de trabajo
    public ResponseEntity<?> altaOrden(@RequestBody @Valid OrdenNuevaDTO orden){
        OrdenTrabajo nueva=this.service.altaOrden(this.nuevaMapper.mapToEntity(orden));
        OrdenNuevaDTO nuevaResp=this.nuevaMapper.mapToDto(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResp);
    }

    @PutMapping("/{id}/manoobra")
    //Necesita el id de mecanico solamente
    public ResponseEntity<?> altaManoObra(@PathVariable Long id,
                                          @RequestBody @Valid ManoObraMecanicoDTO dto){
        ManoObra obra=this.manoObraMapper.mapToEntity(dto);
        OrdenTrabajo orden=this.service.altaManoObra(id, obra);
        OrdenTrabajoRespDTO ordenResp=this.ordenRespMapper.mapToDto(orden);
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/reparacion")
    //inicia reparacion
    public ResponseEntity<?> modiOrden(@PathVariable Long id){
        OrdenTrabajoRespDTO dtoResp=this.ordenRespMapper.mapToDto(this.service.iniciarReparacion(id));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dtoResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/finalizacion")
    //La orden va a venir con las mano de obras (existentes ya) con campos completos
    //y traera los detalles que necesite crear
    public ResponseEntity<?> finalizaReparacion(@PathVariable Long id,
                                                @RequestBody @Valid OrdenDetalladaDTO dto){
        OrdenTrabajo orden=this.detalladaMapper.mapToEntity(dto);
        OrdenDetalladaDTO dtoResp=this.detalladaMapper.mapToDto(this.service.finalizarReparacion(id,orden));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dtoResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/facturacion")
    public ResponseEntity<?> facturarOrden(@PathVariable Long id,
                                           @RequestBody @Valid OrdenFacturadaDTO dto){
        OrdenTrabajo orden=this.factuMapper.mapToEntity(dto);
        OrdenFacturadaDTO ordenFactu=this.factuMapper.mapToDto(this.service.facturarOrden(id,orden));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenFactu);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/cierre")
    //Recepcionista cierra la orden. Se supone que es la misma que la generó.
    public ResponseEntity<?> cerrarOrden(@PathVariable Long id){
        OrdenTrabajo orden=this.service.cerrarOrden(id);
        OrdenTrabajoRespDTO dto=this.ordenRespMapper.mapToDto(orden);
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dto);
        return ResponseEntity.ok(mensajeBody);
    }


    @GetMapping
    public ResponseEntity<?> verTodas(){
        List<OrdenTrabajoRespDTO> ordenes=this.ordenRespMapper.mapListToDto(this.service.verTodas());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenes);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/vehiculos/{patente}")
    public ResponseEntity<?> verOrdenesByPatentes(@PathVariable String patente){
        return null;
    }
}
