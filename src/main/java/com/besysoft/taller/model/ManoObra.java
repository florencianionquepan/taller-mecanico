package com.besysoft.taller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManoObra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    @Temporal(TemporalType.TIME)
    private Date duracionHs;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value="listaManoObra")
    private Mecanico mecanico;

    @ManyToOne(cascade = CascadeType.MERGE)
    private OrdenTrabajo ordenTrabajo;

    @Override
    public String toString() {
        return "ManoObra{" +
                "id=" + id +
                ", detalle='" + detalle + '\'' +
                ", duracionHs=" + duracionHs +
                ", ordenTrabajo=" + ordenTrabajo +
                '}';
    }
}
