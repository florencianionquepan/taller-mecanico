package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteDTO {
    private Long id;

    @Embedded
    @JsonProperty("datosPersonales")
    private PersonaDTO personaDTO;

    private String tel;
    @NotEmpty
    @NotNull
    @Email
    private String email;

    @JsonIgnoreProperties(value="clientes")
    private List<VehiculoDTO> vehiculos;
}
