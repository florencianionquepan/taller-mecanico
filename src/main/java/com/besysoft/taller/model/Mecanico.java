package com.besysoft.taller.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="mecanicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
