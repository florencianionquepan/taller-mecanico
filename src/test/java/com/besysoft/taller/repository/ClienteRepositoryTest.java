package com.besysoft.taller.repository;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repo;

    @BeforeEach
    void setUp() {
        repo.save(DatosDummy.getClienteIgor());
        repo.save(DatosDummy.getClienteEmma());
    }

    @Test
    void buscarPorEmail() {
        //GIVEN
        String email="igor@yahoo.com";

        //WHEN
        Optional<Cliente> oCli=repo.buscarPorEmail(DatosDummy.getClienteEmma().getCorreoElectronico());

        //THEN
        assertThat(oCli.isPresent()).isTrue();
        assertThat(oCli.get().getCorreoElectronico().equals(email));
    }
}