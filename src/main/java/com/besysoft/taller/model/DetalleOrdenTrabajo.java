package com.besysoft.taller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="detalleOrdenesTrabajo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrdenTrabajo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private double valorTotal;

    @OneToOne(cascade = CascadeType.MERGE)
    private OrdenTrabajo ordenTrabajo;
    @OneToOne(cascade = CascadeType.MERGE)
    private Repuesto repuesto;
}
