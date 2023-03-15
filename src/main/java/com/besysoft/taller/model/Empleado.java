package com.besysoft.taller.model;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(getId(), empleado.getId()) && Objects.equals(getPersona(), empleado.getPersona());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPersona());
    }
}
