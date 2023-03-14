package com.besysoft.taller.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue(value="Recep")
public class Recepcionista extends Empleado implements Serializable {

}
