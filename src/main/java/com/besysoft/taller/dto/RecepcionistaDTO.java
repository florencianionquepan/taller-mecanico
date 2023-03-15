package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Embedded;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecepcionistaDTO {
    private Long id;

    @Embedded
    @JsonProperty("datosPersonales")
    private PersonaDTO personaDTO;
}
