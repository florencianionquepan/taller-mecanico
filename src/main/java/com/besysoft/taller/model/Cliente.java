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
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nombres;
    @Column(length = 80, nullable = false)
    private String apellido;
    @Column(length = 15)
    private String celular;
    private String localidad;
    private String codigoPostal;
    private String calle;
    private String numero;
    private String departamento;
    private String piso;

    @Column(length = 15)
    private String telefonoLinea;

    @Column(nullable = false,unique = true)
    private String correoElectronico;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name="cliente_vehiculo",
            joinColumns = @JoinColumn(name="vehiculo_id"),
            inverseJoinColumns = @JoinColumn (name="cliente_id")
    )
    private List<Vehiculo> listaVehiculos;

}
