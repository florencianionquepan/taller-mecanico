package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Repuesto;
import com.besysoft.taller.repository.RepuestoRepository;
import com.besysoft.taller.service.interfaces.IRepuestoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class RepuestoServiceImpleTest {


    private RepuestoRepository repo;
    private IRepuestoService service;

    @BeforeEach
    void setUp() {
        repo=mock(RepuestoRepository.class);
        service=new RepuestoServiceImple(repo);
    }

    @Test
    void altaRepuesto() {
        Repuesto rep= DatosDummy.getRepuestoUno();
        when(repo.save(rep))
                .thenReturn(rep);

        //WHEN
        service.altaRepuesto(rep);

        //THEN
        ArgumentCaptor<Repuesto> repuestoArgumentCaptor=ArgumentCaptor.forClass(Repuesto.class);
        verify(repo).save(repuestoArgumentCaptor.capture());
    }

    @Test
    void verRepuestos() {
        Repuesto rep= DatosDummy.getRepuestoUno();
        when(repo.findAll())
                .thenReturn(new ArrayList<Repuesto>(
                        List.of(DatosDummy.getRepuestoUno())
                ));

        List<Repuesto> lista=service.verRepuestos();

        assertThat(lista.size()).isEqualTo(1);
        verify(repo).findAll();
    }

    @Test
    void buscarById() {
        Repuesto rep=DatosDummy.getRepuestoUno();
        when(repo.save(rep))
                .thenReturn(rep);
        when(repo.findById(anyLong()))
                .thenReturn(Optional.of(rep));

        Repuesto expected=service.buscarById(anyLong());

        assertThat(expected).isEqualTo(rep);
        verify(repo).findById(anyLong());
    }

    @Test
    void buscarByIdException() {
        Repuesto rep=DatosDummy.getRepuestoUno();
        when(repo.findById(rep.getId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->service.buscarById(rep.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El repuesto con id %d no existe",
                        rep.getId()));
    }
}