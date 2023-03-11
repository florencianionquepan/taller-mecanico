package com.besysoft.taller.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue("Admin")
public class Administrativo extends Empleado implements Serializable {
    //private OrdenTrabajo ordenTrabajo;
}
