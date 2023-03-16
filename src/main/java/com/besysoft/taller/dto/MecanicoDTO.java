package com.besysoft.taller.dto;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Persona;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MecanicoDTO {
    @NotNull
    private Long id;

    @Embedded
    @JsonProperty("datosPersonales")
    @Valid
    private PersonaDTO personaDTO;

    private Character activo;
    private String especialidad;

    private List<ManoObra> listaManoObra;
}