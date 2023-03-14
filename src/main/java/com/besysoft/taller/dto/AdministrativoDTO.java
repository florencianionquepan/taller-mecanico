package com.besysoft.taller.dto;
import com.besysoft.taller.model.Persona;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
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
    private PersonaDTO personaDTO;
}
