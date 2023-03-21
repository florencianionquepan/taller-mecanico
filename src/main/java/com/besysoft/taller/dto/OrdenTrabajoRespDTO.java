package com.besysoft.taller.dto;

import com.besysoft.taller.model.DetalleOrdenTrabajo;
import com.besysoft.taller.model.EstadoOrden;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenTrabajoRespDTO {
    private Long id;
    private EstadoOrden estado;
    private String nivelCombustible;
    private Long kilometraje;
    private String detalleFalla;

    private LocalDateTime fechaIngreso;

    private LocalDateTime fechaPago;
    private String formaPago;
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    private BigDecimal importeTotal;

    private LocalDateTime fechaFinReparacion;

    private VehiculoDTO vehiculo;

    @JsonIgnoreProperties(value="ordenTrabajo")
    private List<ManoObraDTO> listaManoObra;

    @JsonIgnoreProperties(value="ordenTrabajo")
    private List<DetalleOrdenTrabajo> listaDetalleOrdenes;

}
