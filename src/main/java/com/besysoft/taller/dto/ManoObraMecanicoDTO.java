package com.besysoft.taller.dto;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//este DTO lo utiliza OrdenTrabajoDTO
public class ManoObraMecanicoDTO {
    @NotNull
    @Size(min=1)
    private Long id;
    @NotNull
    @NotEmpty
    private String detalle;
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date duracionHs;

    @NotNull
    @Valid
    private MecanicoDTO mecanico;
}
