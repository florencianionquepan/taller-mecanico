package com.besysoft.taller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Persona persona;

    @Column(length = 15)
    private String telefonoLinea;

    @Column(nullable = false,unique = true)
    private String correoElectronico;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name="cliente_vehiculo",
            joinColumns = @JoinColumn(name="cliente_id"),
            inverseJoinColumns = @JoinColumn (name="vehiculo_id")
    )
    @JsonIgnoreProperties(value="listaClientes")
    private List<Vehiculo> listaVehiculos;

}
