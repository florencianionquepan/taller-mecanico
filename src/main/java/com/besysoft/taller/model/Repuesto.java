package com.besysoft.taller.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="repuestos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repuesto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String repuesto;
    @Column(nullable = false)
    @Digits(integer = 17,fraction = 2)
    private BigDecimal valor;

    @OneToMany(mappedBy = "repuesto",cascade = CascadeType.MERGE)
    private List<DetalleOrdenTrabajo> listadetalleOrdenes;
}
