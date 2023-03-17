package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//ESTE DTO si debera poseer mecanico y orden
public class ManoObraReqDTO {
    private Long id;
    private String detalle;
    private Time duracionHs;

    @NotNull
    @Valid
    private MecanicoDTO mecanico;

    @NotNull
    @Valid
    private OrdenTrabajoDTO ordenTrabajo;
}
