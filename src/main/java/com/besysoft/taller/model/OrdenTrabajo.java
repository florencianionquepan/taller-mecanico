package com.besysoft.taller.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
public class OrdenTrabajo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private String nivelCombustible;
    private Long kilometraje;
    private String detalleFalla;
    private LocalDate fechaIngreso;
    private LocalDate fechaPago;
    private String formaPago;
    private int cantidadCuotas;
    private String tipoTarjeta;
    private double importeTotal;
    private LocalDate fechaFinReparacion;

    @OneToOne(cascade = CascadeType.MERGE)
    private Recepcionista recepcionista;
    @OneToOne(cascade = CascadeType.MERGE)
    private Administrativo administrativo;
    private Vehiculo vehiculo;
}
