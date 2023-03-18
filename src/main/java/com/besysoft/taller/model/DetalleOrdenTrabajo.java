package com.besysoft.taller.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    private OrdenTrabajo ordenTrabajo;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Repuesto repuesto;
}
