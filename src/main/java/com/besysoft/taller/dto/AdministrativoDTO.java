package com.besysoft.taller.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdministrativoDTO {

    private Long id;

    @Embedded
    @JsonProperty("datosPersonales")
    @Valid
    private PersonaDTO personaDTO;
}
