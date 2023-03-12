package com.besysoft.taller.repository;


import com.besysoft.taller.model.Vehiculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehiculoRepository extends CrudRepository<Vehiculo,Long> {

    @Query("select v from Vehiculo v where v.patente=?1")
    Optional<Vehiculo> buscarPorPatente(String patente);
}
