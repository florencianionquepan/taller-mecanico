package com.besysoft.taller.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="empleados")
@DiscriminatorColumn(name="tipo_empleado")
public abstract class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellido;
    private String celular;
    private String localidad;
    private String codigoPostal;
    private String calle;
    private String numero;
    private String departamento;
    private String piso;
}
