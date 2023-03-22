package com.besysoft.taller.service.implementation;

import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrdenServiceImpleTest {

    private OrdenTrabajoRepository repo;
    private IRecepcionService recepService;
    private IVehiculoService vehiService;
    private ManoObraRepository manoObraRepo;
    private IDetalleOrdenService detalleOrdenService;
    private IAdminService adminService;
    private IMecanicoService mecaService;
    private IManoObraService obraService;
    private IOrdenService service;

    @BeforeEach
    void setUp() {
        repo=mock(OrdenTrabajoRepository.class);
        recepService=mock(IRecepcionService.class);
        vehiService=mock(IVehiculoService.class);
        manoObraRepo=mock(ManoObraRepository.class);
        detalleOrdenService=mock(IDetalleOrdenService.class);
        adminService=mock(IAdminService.class);
        mecaService=mock(IMecanicoService.class);
        obraService=mock(IManoObraService.class);
        service=new OrdenServiceImple(repo,recepService,vehiService,manoObraRepo,
                detalleOrdenService,adminService,mecaService,obraService);
    }

    @Test
    void altaOrden() {
    }

    @Test
    void altaManoObra() {
    }

    @Test
    void iniciarReparacion() {
    }

    @Test
    void finalizarReparacion() {
    }

    @Test
    void facturarOrden() {
    }

    @Test
    void cerrarOrden() {
    }

    @Test
    void verTodas() {
    }

    @Test
    void buscarById() {
    }

    @Test
    void verByVehiculo() {
    }
}