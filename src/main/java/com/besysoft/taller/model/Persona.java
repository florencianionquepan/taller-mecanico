package com.besysoft.taller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Column(length = 100, nullable = false)
    private String nombres;
    @Column(length = 80, nullable = false)
    private String apellido;
    @Column(length = 15)
    private String celular;
    @Embedded
    private Direccion direccion;
}
