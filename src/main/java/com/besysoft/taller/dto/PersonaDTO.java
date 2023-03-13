package com.besysoft.taller.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class PersonaDTO {

    @NotNull
    @NotEmpty
    @Size(max=100)
    private String nombres;
    @NotNull
    @NotEmpty
    @Size(max=80)
    private String apellido;
    @NotNull
    @NotEmpty
    @Size(max=15)
    private String celular;

    private String localidad;
    private String codigoPostal;
    private String calle;
    private String numero;
    private String departamento;
    private String piso;

}
