package com.besysoft.taller.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@DiscriminatorValue("Admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrativo extends Empleado implements Serializable {

}
