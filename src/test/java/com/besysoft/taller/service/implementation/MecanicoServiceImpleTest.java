package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Mecanico;
import com.besysoft.taller.repository.AdminRepository;
import com.besysoft.taller.repository.MecanicoRepository;
import com.besysoft.taller.service.interfaces.IMecanicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MecanicoServiceImpleTest {

    private MecanicoRepository repo;
    private IMecanicoService service;

    @BeforeEach
    void setUp() {
        repo=mock(MecanicoRepository.class);
        service=new MecanicoServiceImple(repo);
    }

    @Test
    void altaMecanico() {
        Mecanico meca= DatosDummy.getMeca();
        when(repo.save(meca))
                .thenReturn(meca);

        service.altaMecanico(meca);

        ArgumentCaptor<Mecanico> mecaArgumentCaptor=ArgumentCaptor.forClass(Mecanico.class);
        verify(repo).save(mecaArgumentCaptor.capture());

        Mecanico mecaCaptor=mecaArgumentCaptor.getValue();
        assertThat(mecaCaptor).isEqualTo(meca);
    }

    @Test
    void verActivos() {
        when(repo.findAll())
                .thenReturn(new ArrayList<Mecanico>(
                        List.of(DatosDummy.getMeca())
                ));
        List<Mecanico> lista=service.verActivos();

        assertThat(lista.size()).isEqualTo(1);
        verify(repo).findAll();
    }

    @Test
    void buscarById() {
        Mecanico meca= DatosDummy.getMeca();
        when(repo.findById(anyLong()))
                .thenReturn(Optional.of(meca));

        Mecanico expected=service.buscarById(anyLong());

        assertThat(expected).isEqualTo(meca);
        verify(repo).findById(anyLong());
    }

    @Test
    void buscarByIdException() {
        when(repo.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->service.buscarById(anyLong()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El mecanico con id %d no existe ",
                        anyLong()));
    }

    @Test
    void addManoObra() {
        //GIVEN
        Mecanico meca=DatosDummy.getMeca();
        ManoObra obra=DatosDummy.getMO();
        //WHEN
        service.addManoObra(meca,obra);
        //THEN
        assertThat(meca.getListaManoObra().contains(obra)).isTrue();
    }

    @Test
    void mecanicoConMenosObras() {
    }
}