package com.besysoft.taller.repository;

import com.besysoft.taller.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {

    @Query("select c from Cliente c where c.correoElectronico=?1")
    Optional<Cliente> buscarPorEmail(String correo);
}
