package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.service.interfaces.IManoObraService;
import com.besysoft.taller.service.interfaces.IMecanicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ManoObraServiceImpleTest {

    private ManoObraRepository repo;
    private IManoObraService service;
    private IMecanicoService mecaService;

    @BeforeEach
    void setUp() {
        repo=mock(ManoObraRepository.class);
        mecaService=mock(IMecanicoService.class);
        service=new ManoObraServiceImple(repo,mecaService);
    }

    @Test
    void altaManoObra() {
        //GIVEN
        ManoObra obra= DatosDummy.getMOActiva();
        when(repo.save(obra))
                .thenReturn(obra);
        //WHEN
        service.altaManoObra(obra);
        //THEN
        ArgumentCaptor<ManoObra> obraArgumentCaptor=ArgumentCaptor.forClass(ManoObra.class);
        verify(repo).save(obraArgumentCaptor.capture());

        ManoObra obraCaptor=obraArgumentCaptor.getValue();
        assertThat(obraCaptor).isEqualTo(obra);
    }
}