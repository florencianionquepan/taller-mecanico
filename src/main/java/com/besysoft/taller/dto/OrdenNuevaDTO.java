package com.besysoft.taller.dto;

import com.besysoft.taller.model.EstadoOrden;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    private LocalDateTime fechaIngreso;

    @NotNull
    private RecepcionistaDTO recepcionista;

    @NotNull
    @Valid
    private VehiculoDTO vehiculo;
}
