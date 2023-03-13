package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

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
