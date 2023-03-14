package com.besysoft.taller.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue("Admin")
public class Administrativo extends Empleado implements Serializable {

}
