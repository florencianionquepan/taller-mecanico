package com.besysoft.taller.model;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Time;

@Entity
public class ManoObra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    private Time duracionHs;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Mecanico mecanico;

    @OneToOne(cascade = CascadeType.MERGE)
    private OrdenTrabajo ordenTrabajo;

}
