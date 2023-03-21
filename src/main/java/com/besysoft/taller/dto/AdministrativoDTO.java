package com.besysoft.taller.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonPropertyOrder({"id", "personaDTO"})
public class AdministrativoDTO {
    @NotNull
    private Long id;

    @Embedded
    @JsonProperty("datosPersonales")
    @Valid
    private PersonaDTO personaDTO;
}
