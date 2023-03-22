package com.besysoft.taller.service.implementation;

import com.besysoft.taller.repository.DetalleOrdenRepository;
import com.besysoft.taller.service.interfaces.IRepuestoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DetalleOrdenServiceImpleTest {

    private DetalleOrdenRepository repo;
    private IRepuestoService repuService;
    private DetalleOrdenServiceImple service;

    @BeforeEach
    void setUp() {
        repo=mock(DetalleOrdenRepository.class);
        repuService=mock(IRepuestoService.class);
        service=new DetalleOrdenServiceImple(repo,repuService);
    }

    @Test
    void altaDetalleOrden() {
    }
}