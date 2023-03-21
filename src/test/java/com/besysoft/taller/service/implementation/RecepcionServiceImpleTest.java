package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Recepcionista;
import com.besysoft.taller.repository.RecepcionRepository;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RecepcionServiceImpleTest {

    private RecepcionRepository repo;
    private IRecepcionService service;

    @BeforeEach
    void setUp() {
        repo=mock(RecepcionRepository.class);
        service=new RecepcionServiceImple(repo);
    }

    @Test
    void altaRecepcion() {
        //GIVEN
        Recepcionista recep= DatosDummy.getRecep();
        recep.setPersona(DatosDummy.getPersonaUno());
        when(repo.save(recep))
                .thenReturn(recep);

        //WHEN
        service.altaRecepcion(recep);

        //THEN
        ArgumentCaptor<Recepcionista> recepArgumentCaptor=ArgumentCaptor.forClass(Recepcionista.class);
        verify(repo).save(recepArgumentCaptor.capture());

        Recepcionista recepCaptor=recepArgumentCaptor.getValue();
        assertThat(recepCaptor).isEqualTo(recep);
    }

    @Test
    void verTodas() {
        //GIVEN
        Recepcionista recep= DatosDummy.getRecep();
        recep.setPersona(DatosDummy.getPersonaUno());
        when(repo.findAll())
                .thenReturn(new ArrayList<>(
                        List.of(recep)
                        ));
        List<Recepcionista> lista=service.verTodas();

        assertThat(lista.size()).isEqualTo(1);
        verify(repo).findAll();
    }

    @Test
    void buscarById() {
        //GIVEN
        Recepcionista recep= DatosDummy.getRecep();
        recep.setPersona(DatosDummy.getPersonaUno());
        when(repo.save(recep))
                .thenReturn(recep);
        when(repo.findById(anyLong()))
                .thenReturn(Optional.of(recep));

        //WHEN
        Recepcionista recepExpec=service.buscarById(anyLong());

        //THEN
        assertThat(recepExpec).isEqualTo(recep);
        verify(repo).findById(anyLong());
    }

    @Test
    void buscarByIdException() {
        //GIVEN
        when(repo.findById(anyLong()))
                .thenReturn(Optional.empty());

        //WHEN
        //THEN
        assertThatThrownBy(()->service.buscarById(anyLong()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La recepcionista con id %d no existe",
                        anyLong()));
    }
}