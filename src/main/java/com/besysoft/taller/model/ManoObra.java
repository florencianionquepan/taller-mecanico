package com.besysoft.taller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManoObra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    private Time duracionHs;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="listaManoObra")
    private Mecanico mecanico;

    @OneToOne(cascade = CascadeType.MERGE)
    private OrdenTrabajo ordenTrabajo;

}
