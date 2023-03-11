package com.besysoft.taller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="ordenesTrabajo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToOne(cascade = CascadeType.MERGE)
    private Vehiculo vehiculo;
}
