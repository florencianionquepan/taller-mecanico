package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @Valid
    private PersonaDTO personaDTO;

    private String tel;
    @NotEmpty
    @NotNull
    @Email
    private String email;

    @JsonIgnoreProperties(value="clientes")
    private List<VehiculoDTO> vehiculos;
}
