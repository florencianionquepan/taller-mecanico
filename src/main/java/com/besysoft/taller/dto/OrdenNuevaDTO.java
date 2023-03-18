package com.besysoft.taller.dto;

import com.besysoft.taller.model.EstadoOrden;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenNuevaDTO {
    private Long id;
    private EstadoOrden estado;
    @NotNull
    @NotEmpty
    private String nivelCombustible;
    @NotNull
    private Long kilometraje;
    @NotNull
    @NotEmpty
    private String falla;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @NotNull
    @Valid
    private RecepcionistaDTO recepcionista;

    @NotNull
    @Valid
    private VehiculoDTO vehiculo;
}
