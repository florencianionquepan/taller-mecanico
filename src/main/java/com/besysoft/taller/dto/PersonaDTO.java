package com.besysoft.taller.dto;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
