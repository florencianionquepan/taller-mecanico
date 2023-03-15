package com.besysoft.taller.model;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona {

    @Column(length = 100, nullable = false)
    private String nombres;
    @Column(length = 80, nullable = false)
    private String apellido;
    @Column(length = 15)
    private String celular;

    private String localidad;
    private String codigoPostal;
    private String calle;
    private String numero;
    private String departamento;
    private String piso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(getNombres(), persona.getNombres()) && Objects.equals(getApellido(), persona.getApellido()) && Objects.equals(getCelular(), persona.getCelular()) && Objects.equals(getLocalidad(), persona.getLocalidad()) && Objects.equals(getCodigoPostal(), persona.getCodigoPostal()) && Objects.equals(getCalle(), persona.getCalle()) && Objects.equals(getNumero(), persona.getNumero()) && Objects.equals(getDepartamento(), persona.getDepartamento()) && Objects.equals(getPiso(), persona.getPiso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombres(), getApellido(), getCelular(), getLocalidad(), getCodigoPostal(), getCalle(), getNumero(), getDepartamento(), getPiso());
    }
}
