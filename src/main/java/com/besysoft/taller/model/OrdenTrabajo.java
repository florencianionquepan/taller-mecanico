package com.besysoft.taller.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Enumerated(EnumType.STRING)
    private EstadoOrden estado;
    private String nivelCombustible;
    private Long kilometraje;
    private String detalleFalla;

    private LocalDateTime fechaIngreso;

    private LocalDateTime fechaPago;
    private String formaPago;
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    @Digits(integer=17,fraction = 2)
    private BigDecimal importeTotal;

    private LocalDateTime fechaFinReparacion;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Recepcionista recepcionista;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Administrativo administrativo;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "ordenTrabajo",cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="ordenTrabajo")
    private List<ManoObra> listaManoObra;

    @OneToMany(mappedBy = "ordenTrabajo",cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="ordenTrabajo")
    private List<DetalleOrdenTrabajo> listaDetalleOrdenes;
}
