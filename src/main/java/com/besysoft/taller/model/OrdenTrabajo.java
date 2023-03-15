package com.besysoft.taller.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ordenesTrabajo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdenTrabajo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private String nivelCombustible;
    private Long kilometraje;
    private String detalleFalla;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    private String formaPago;
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    @Digits(integer=17,fraction = 2)
    private BigDecimal importeTotal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinReparacion;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Recepcionista recepcionista;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Administrativo administrativo;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "ordenTrabajo",cascade = CascadeType.MERGE)
    private List<DetalleOrdenTrabajo> listaDetalleOrdenes;
}
