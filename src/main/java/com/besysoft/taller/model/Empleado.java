package com.besysoft.taller.model;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="empleados")
@DiscriminatorColumn(name="tipo_empleado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Persona persona;

}
