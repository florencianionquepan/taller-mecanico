package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//DTO de mano de obra sin mecanico asignado.Su mapper sera inyectado por MecanicoMapper
public class ManoObraOrdenDTO {
    @NonNull
    private Long id;
    private String detalle;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime duracionHs;

    @NotNull
    @Valid
    @JsonIgnoreProperties(value={"recepcionista","administrativo"})
    private OrdenTrabajoDTO ordenTrabajo;
}
