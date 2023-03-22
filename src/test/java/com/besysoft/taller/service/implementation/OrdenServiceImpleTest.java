package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.*;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        OrdenTrabajo creada= DatosDummy.getOrdenCreada();
        Recepcionista recep= creada.getRecepcionista();
        Vehiculo ve=creada.getVehiculo();
        when(repo.save(creada))
                .thenReturn(creada);
        when(recepService.buscarById(creada.getRecepcionista().getId()))
                .thenReturn(recep);
        when(vehiService.buscarPorPatente(creada.getVehiculo().getPatente()))
                .thenReturn(ve);

        service.altaOrden(creada);

        ArgumentCaptor<OrdenTrabajo> ordenArgumentCaptor=ArgumentCaptor
                .forClass(OrdenTrabajo.class);
        verify(repo).save(ordenArgumentCaptor.capture());

        OrdenTrabajo ordenCaptor=ordenArgumentCaptor.getValue();
        assertThat(ordenCaptor).isEqualTo(creada);
    }

    @Test
    void altaManoObra() {
        ManoObra prueba=DatosDummy.getMOMecanico();
        Mecanico meca=prueba.getMecanico();
        OrdenTrabajo creada=DatosDummy.getOrdenCreada();
        when(repo.findById(creada.getId()))
                .thenReturn(Optional.of(creada));
        when(mecaService.buscarById(prueba.getMecanico().getId()))
                .thenReturn(meca);
        when(obraService.altaManoObra(prueba))
                .thenReturn(new ManoObra(1L,null,null,meca,creada));
        OrdenTrabajo act=creada;
        act.getListaManoObra().add(new ManoObra(1L,null,null,meca,creada));
        when(repo.findById(creada.getId()))
                .thenReturn(Optional.of(act));

        OrdenTrabajo expected=service.altaManoObra(creada.getId(), prueba);

        assertThat(expected.getListaManoObra().size()).isEqualTo(1);
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