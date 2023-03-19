package com.besysoft.taller.dto;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//ESTE DTO si debera poseer mecanico y orden, cuando se genera mano de obra
public class ManoObraMecanicoOrdenDTO {
    private Long id;

    @NotNull
    @Valid
    private MecanicoDTO mecanico;

    @NotNull
    @Valid
    private OrdenTrabajoDTO ordenTrabajo;
}
