package com.besysoft.taller.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue(value="Recep")
public class Recepcionista extends Empleado implements Serializable {

}
