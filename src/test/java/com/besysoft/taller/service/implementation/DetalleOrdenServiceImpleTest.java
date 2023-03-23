package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.DetalleOrdenTrabajo;
import com.besysoft.taller.model.Repuesto;
import com.besysoft.taller.repository.DetalleOrdenRepository;
import com.besysoft.taller.service.interfaces.IDetalleOrdenService;
import com.besysoft.taller.service.interfaces.IRepuestoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DetalleOrdenServiceImpleTest {

    private DetalleOrdenRepository repo;
    private IRepuestoService repuService;
    private IDetalleOrdenService service;

    @BeforeEach
    void setUp() {
        repo=mock(DetalleOrdenRepository.class);
        repuService=mock(IRepuestoService.class);
        service=new DetalleOrdenServiceImple(repo,repuService);
    }

    @Test
    void altaDetalleOrden() {
        DetalleOrdenTrabajo detalle= DatosDummy.getDetalle();
        Repuesto repu=detalle.getRepuesto();
        when(repuService.buscarById(repu.getId()))
                .thenReturn(repu);
        when(repo.save(detalle))
                .thenReturn(detalle);

        service.altaDetalleOrden(detalle);

        ArgumentCaptor<DetalleOrdenTrabajo> detalleArgumentCaptor=ArgumentCaptor
                .forClass(DetalleOrdenTrabajo.class);
        verify(repo).save(detalleArgumentCaptor.capture());

        DetalleOrdenTrabajo detalleCaptor=detalleArgumentCaptor.getValue();
        assertThat(detalleCaptor).isEqualTo(detalle);
    }
}