package com.besysoft.taller.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="mecanicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mecanico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Persona persona;

    private Character activo;
    private String especialidad;

    @OneToMany(mappedBy = "mecanico", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = "mecanico")
    private List<ManoObra> listaManoObra;
}
