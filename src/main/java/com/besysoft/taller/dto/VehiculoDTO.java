package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehiculoDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String patente;
    private Integer anio;
    private String color;
    private String marca;
    private String modelo;

    @JsonIgnoreProperties(value="vehiculos")
    private List<ClienteDTO> clientes;

}
