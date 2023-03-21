package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"id", "personaDTO", "activo","especialidad","listaManoObra"})
public class MecanicoDTO {
    @NotNull
    private Long id;

    @Embedded
    @JsonProperty("datosPersonales")
    @Valid
    private PersonaDTO personaDTO;

    private Character activo;
    private String especialidad;

    @JsonIgnoreProperties(value = "mecanico")
    private List<ManoObraOrdenDTO> listaManoObra;
}
