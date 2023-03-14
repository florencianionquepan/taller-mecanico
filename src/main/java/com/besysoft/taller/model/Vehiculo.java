package com.besysoft.taller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="vehiculos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String patente;
    private Integer anio;
    private String color;
    private String marca;
    private String modelo;

    @ManyToMany(mappedBy = "listaVehiculos",cascade = {CascadeType.MERGE})
    @JsonIgnoreProperties(value="listaVehiculos")
    private List<Cliente> listaClientes;
}
