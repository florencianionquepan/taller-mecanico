package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.*;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
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
        //WHEN
        OrdenTrabajo expected=service.altaManoObra(creada.getId(), prueba);
        //THEN
        assertThat(expected.getListaManoObra().size()).isEqualTo(1);
    }

    @Test
    void iniciarReparacion() {
        OrdenTrabajo creada=DatosDummy.getOrdenCreada();
        creada.setListaManoObra(new ArrayList<ManoObra>(
                List.of(DatosDummy.getMOActiva())
        ));
        when(repo.findById(creada.getId()))
                .thenReturn(Optional.of(creada));
        //WHEN
        service.iniciarReparacion(creada.getId());
        //THEN
        assertThat(creada.getEstado()).isEqualTo(EstadoOrden.REPARACION);
    }

    @Test
    void iniciarReparacionSinObras() {
        OrdenTrabajo creada=DatosDummy.getOrdenCreada();
        creada.setListaManoObra(new ArrayList<ManoObra>());
        when(repo.findById(creada.getId()))
                .thenReturn(Optional.of(creada));
        //WHEN
        //THEN
        assertThatThrownBy(()->service.iniciarReparacion(creada.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La orden no posee mano de obra" +
                        ".Debe generarla para luego iniciar la reparacion"));
    }

    @Test
    void iniciarReparacionMalEstado() {
        OrdenTrabajo cerrada=DatosDummy.getOrdenCerrada();
        when(repo.findById(cerrada.getId()))
                .thenReturn(Optional.of(cerrada));
        //WHEN
        //THEN
        assertThatThrownBy(()->service.iniciarReparacion(cerrada.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La orden esta en estado %s" +
                                ".No puede iniciar la reparacion de esta orden"
                        ,cerrada.getEstado()));
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