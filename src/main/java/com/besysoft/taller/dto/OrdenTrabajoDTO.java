package com.besysoft.taller.dto;

import com.besysoft.taller.model.EstadoOrden;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenTrabajoDTO {
    @NotNull
    private Long id;
    private EstadoOrden estado;
    private String nivelCombustible;
    private Long kilometraje;
    private String falla;

    private LocalDateTime fechaIngreso;

    //estaran nulos al momento de crearse
    private LocalDateTime fechaPago;
    private String formaPago;
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    private BigDecimal importeTotal;

    private LocalDateTime fechaFinReparacion;

    private RecepcionistaDTO recepcionista;

    //se solicitara en facturada
    private AdministrativoDTO administrativo;

    private VehiculoDTO vehiculo;

}
