package com.besysoft.taller.dto;

import com.besysoft.taller.model.EstadoOrden;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenDetalladaDTO {
    private Long id;
    private EstadoOrden estado;

    private LocalDateTime fechaFinReparacion;
    private BigDecimal importeTotal;
    @JsonIgnoreProperties(value="clientes")
    private VehiculoDTO vehiculo;
    @NotNull
    @Valid
    private List<ManoObraDTO> listaManoObra;
    @NotNull
    @Valid
    private List<DetalleOrdenDTO> detalleOrdenes;
}
