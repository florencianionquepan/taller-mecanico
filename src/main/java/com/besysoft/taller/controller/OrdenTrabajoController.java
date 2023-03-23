package com.besysoft.taller.controller;

import com.besysoft.taller.dto.*;
import com.besysoft.taller.dto.mapper.*;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.service.interfaces.IOrdenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value="Orden de Trabajo Controller", tags="Orden de Trabajo Controller")
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
    @ApiOperation(value="Permite generar una nueva orden de trabajo. Se debe registrar" +
            " la recepcionista a cargo de esta operación")
    public ResponseEntity<?> altaOrden(@RequestBody @Valid OrdenNuevaDTO orden){
        OrdenTrabajo nueva=this.service.altaOrden(this.nuevaMapper.mapToEntity(orden));
        OrdenNuevaDTO nuevaResp=this.nuevaMapper.mapToDto(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResp);
    }

    @PutMapping("/{id}/manoobra")
    @ApiOperation(value="Permite generar una mano de obra asociada a una orden de trabajo." +
            "Se debe registrar el mecánico asignado a dicha mano de obra, quien será el responsable" +
            " de esta operación")
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
    @ApiOperation(value="Permite iniciar la reparación de una orden de trabajo")
    public ResponseEntity<?> modiOrden(@PathVariable Long id){
        OrdenTrabajoRespDTO dtoResp=this.ordenRespMapper.mapToDto(this.service.iniciarReparacion(id));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dtoResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/finalizacion")
    @ApiOperation(value="Permite finalizar una orden de trabajo con sus manos de obras completas " +
            "y todos los detalles de ordenes de trabajo")
    //Se deben si o si agregar todos los detalles de ordenes de trabajo en este punto.
    //Luego no es posible agregar otros.
    public ResponseEntity<?> finalizaReparacion(@PathVariable Long id,
                                                @RequestBody @Valid OrdenDetalladaDTO dto){
        OrdenTrabajo orden=this.detalladaMapper.mapToEntity(dto);
        OrdenDetalladaDTO dtoResp=this.detalladaMapper.mapToDto(this.service.finalizarReparacion(id,orden));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dtoResp);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/facturacion")
    @ApiOperation(value="Permite facturar una orden de trabajo. Se debe registrar el administrativo" +
            " a cargo de esta operación y el tipo de pago.")
    public ResponseEntity<?> facturarOrden(@PathVariable Long id,
                                           @RequestBody @Valid OrdenFacturadaDTO dto){
        OrdenTrabajo orden=this.factuMapper.mapToEntity(dto);
        OrdenFacturadaDTO ordenFactu=this.factuMapper.mapToDto(this.service.facturarOrden(id,orden));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenFactu);
        return ResponseEntity.ok(mensajeBody);
    }

    @PutMapping("/{id}/cierre")
    @ApiOperation(value="Permite cerrar una orden de trabajo. La recepcionista será la misma " +
            "que generó dicha orden de trabajo")
    public ResponseEntity<?> cerrarOrden(@PathVariable Long id){
        OrdenTrabajo orden=this.service.cerrarOrden(id);
        OrdenTrabajoRespDTO dto=this.ordenRespMapper.mapToDto(orden);
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",dto);
        return ResponseEntity.ok(mensajeBody);
    }


    @GetMapping
    @ApiOperation(value="Permite listar todas las ordenes de trabajo existentes.")
    public ResponseEntity<?> verTodas(){
        List<OrdenTrabajoRespDTO> ordenes=this.ordenRespMapper.mapListToDto(this.service.verTodas());
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenes);
        return ResponseEntity.ok(mensajeBody);
    }

    @GetMapping("/vehiculos/{patente}")
    @ApiOperation(value="Permite listar las ordenes de trabajo de un vehiculo determinado.")
    public ResponseEntity<?> verOrdenesByPatentes(@PathVariable String patente){
        List<OrdenTrabajoRespDTO> ordenes=this.ordenRespMapper.mapListToDto(this.service.verByVehiculo(patente));
        mensajeBody.put("Success",Boolean.TRUE);
        mensajeBody.put("data",ordenes);
        return ResponseEntity.ok(mensajeBody);
    }
}
