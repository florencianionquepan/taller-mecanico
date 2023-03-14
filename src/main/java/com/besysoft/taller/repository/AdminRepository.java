package com.besysoft.taller.repository;

import com.besysoft.taller.model.Administrativo;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Administrativo,Long> {
}
