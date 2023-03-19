package com.besysoft.taller.dto;

import com.besysoft.taller.model.EstadoOrden;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenAFacturarDTO {
    @NotNull
    private Long id;
    private EstadoOrden estado;

    private LocalDateTime fechaFinReparacion;
    private VehiculoDTO vehiculo;
    @NotNull
    @Valid
    private List<ManoObraDTO> listaManoObra;
    @NotNull
    @Valid
    private List<DetalleOrdenDTO> detalleOrdenes;
}
