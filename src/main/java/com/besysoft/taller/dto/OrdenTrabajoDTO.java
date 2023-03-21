package com.besysoft.taller.dto;

import com.besysoft.taller.model.EstadoOrden;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenTrabajoDTO {
    private Long id;
    private EstadoOrden estado;
    private String nivelCombustible;
    private Long kilometraje;
    private String falla;

    private LocalDateTime fechaIngreso;

    private LocalDateTime fechaPago;
    private String formaPago;
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    private BigDecimal importeTotal;

    private LocalDateTime fechaFinReparacion;

    private VehiculoDTO vehiculo;

}
