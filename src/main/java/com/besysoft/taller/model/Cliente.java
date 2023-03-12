package com.besysoft.taller.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona implements Serializable{

    @Column(length = 15)
    private String telefonoLinea;

    @Column(nullable = false,unique = true)
    private String correoElectronico;

    @OneToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name="cliente_vehiculo",
            joinColumns = @JoinColumn(name="cliente_id"),
            inverseJoinColumns = @JoinColumn (name="vehiculo_id")
    )
    private List<Vehiculo> listaVehiculos;

}
