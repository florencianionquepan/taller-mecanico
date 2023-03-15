package com.besysoft.taller.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue(value="Recep")
public class Recepcionista extends Empleado implements Serializable {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
