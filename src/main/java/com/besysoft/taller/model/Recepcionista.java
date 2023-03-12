package com.besysoft.taller.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@DiscriminatorValue(value="Recep")
public class Recepcionista extends Empleado implements Serializable {

}
