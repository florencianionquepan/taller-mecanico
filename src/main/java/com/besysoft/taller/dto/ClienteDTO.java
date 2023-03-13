package com.besysoft.taller.dto;

import com.besysoft.taller.model.Vehiculo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private PersonaDTO personaDTO;

    private String tel;
    @NotEmpty
    @NotNull
    @Email
    private String email;

    @JsonIgnoreProperties(value="clientes")
    private List<VehiculoDTO> vehiculos;
}
