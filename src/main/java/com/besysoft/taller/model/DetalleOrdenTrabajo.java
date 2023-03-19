package com.besysoft.taller.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="detalleOrdenesTrabajo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleOrdenTrabajo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal valorTotal;

    @ManyToOne
    @JsonIgnoreProperties(value="listaDetalleOrdenes")
    private OrdenTrabajo ordenTrabajo;
    @ManyToOne
    @JsonIgnoreProperties(value="listaDetalleOrdenes")
    private Repuesto repuesto;
}
