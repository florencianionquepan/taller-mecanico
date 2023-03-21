package com.besysoft.taller.repository;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class VehiculoRepositoryTest {

    @Autowired
    private VehiculoRepository repo;

    @BeforeEach
    void setUp() {
        repo.save(DatosDummy.getVehiculoFiat());
        repo.save(DatosDummy.getVehiculoRenault());
    }

    @Test
    void buscarPorPatente() {
        //GIVEN
        String patente="AB123CD";

        //WHEN
        Optional<Vehiculo> oVehi=repo.buscarPorPatente(DatosDummy.getVehiculoRenault().getPatente());

        //THEN
        assertThat(oVehi.isPresent()).isTrue();
        assertThat(oVehi.get().getPatente()).isEqualTo(patente);
    }
}