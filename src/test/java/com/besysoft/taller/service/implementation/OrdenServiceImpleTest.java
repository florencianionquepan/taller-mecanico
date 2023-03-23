package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.*;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        //GIVEN
        OrdenTrabajo repa=DatosDummy.getOrdenReparacion();
        repa.setId(1L);
        ManoObra completa=repa.getListaManoObra().get(0);
        when(repo.findById(repa.getId()))
                .thenReturn(Optional.of(repa));
        when(manoObraRepo.findById(completa.getId()))
                .thenReturn(Optional.of(completa));
        completa.setOrdenTrabajo(repa);
        when(repo.save(repa))
                .thenReturn(repa);
        //WHEN
        OrdenTrabajo modi=service.finalizarReparacion(repa.getId(),repa);
        //THEN
        assertThat(modi.getEstado()).isEqualTo(EstadoOrden.AFACTURAR);
    }

    @Test
    void finalizarReparacionObrasInexistentes() {
        //GIVEN
        OrdenTrabajo repa=DatosDummy.getOrdenReparacion();
        ManoObra completa=new ManoObra(null,"falla aire", LocalTime.of(1,0),
                DatosDummy.getMeca(),null);
        when(repo.findById(repa.getId()))
                .thenReturn(Optional.of(repa));
        when(manoObraRepo.findById(completa.getId()))
                .thenReturn(Optional.empty());
        //WHEN
        //THEN
        assertThatThrownBy(()->service.finalizarReparacion(repa.getId(),repa))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La mano de obra %d no existe ",
                        completa.getId()));
    }

    @Test
    void finalizarReparacionObrasIncorrectas() {
        //GIVEN
        OrdenTrabajo repa=DatosDummy.getOrdenReparacion();
        repa.setId(2L);
        OrdenTrabajo cerrada=DatosDummy.getOrdenCerrada();
        cerrada.setId(1L);
        ManoObra completa=new ManoObra(null,"falla aire", LocalTime.of(1,0),
                DatosDummy.getMeca(),cerrada);
        when(repo.findById(repa.getId()))
                .thenReturn(Optional.of(repa));
        when(manoObraRepo.findById(completa.getId()))
                .thenReturn(Optional.of(completa));
        //WHEN
        //THEN
        assertThatThrownBy(()->service.finalizarReparacion(repa.getId(),repa))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La mano de obra %d no corresponde a la orden ",
                        null));
    }

    @Test
    void finalizarReparacionException() {
        //GIVEN
        OrdenTrabajo creada=DatosDummy.getOrdenCreada();
        when(repo.findById(creada.getId()))
                .thenReturn(Optional.of(creada));
        //WHEN
        //THEN
        assertThatThrownBy(()->service.finalizarReparacion(creada.getId(),creada))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La orden esta en estado %s" +
                                ".No puede finalizar la reparacion de esta orden"
                        ,creada.getEstado()));
    }

    @Test
    void facturarOrdenMalEstado() {
        OrdenTrabajo cerrada=DatosDummy.getOrdenCerrada();
        when(repo.findById(cerrada.getId()))
                .thenReturn(Optional.of(cerrada));
        //WHEN,THEN
        assertThatThrownBy(()->service.facturarOrden(cerrada.getId(),cerrada))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La orden esta en estado %s" +
                                ".No puede facturar ni cobrar esta orden"
                        ,cerrada.getEstado()));
    }

    @Test
    void facturarOrden() {
        OrdenTrabajo reparada=DatosDummy.getOrdenReparada();
        Administrativo admin=new Administrativo();
        admin.setId(1L);
        admin.setPersona(DatosDummy.getPersonaUno());
        reparada.setAdministrativo(admin);
        when(repo.findById(reparada.getId()))
                .thenReturn(Optional.of(reparada));
        when(adminService.buscarById(admin.getId()))
                .thenReturn(admin);
        when(repo.save(reparada))
                .thenReturn(reparada);
        //WHEN
        OrdenTrabajo orden=service.facturarOrden(reparada.getId(),reparada);
        // THEN
        assertThat(orden.getEstado()
                .equals(EstadoOrden.FACTURADA)).isTrue();
    }

    @Test
    void cerrarOrdenMalEstado() {
        OrdenTrabajo creada=DatosDummy.getOrdenCreada();
        when(repo.findById(creada.getId()))
                .thenReturn(Optional.of(creada));
        //WHEN,THEN
        assertThatThrownBy(()->service.cerrarOrden(creada.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La orden esta en estado %s," +
                                " debe ser facturada antes de cerrarse"
                        ,creada.getEstado()));
    }

    @Test
    void cerrarOrden(){
        OrdenTrabajo factu=DatosDummy.getOrdenReparada();
        factu.setEstado(EstadoOrden.FACTURADA);
        when(repo.findById(factu.getId()))
                .thenReturn(Optional.of(factu));
        when(repo.save(factu))
                .thenReturn(factu);
        OrdenTrabajo cerrada=service.cerrarOrden(factu.getId());
        assertThat(cerrada.getEstado().equals(EstadoOrden.CERRADA)).isTrue();
        verify(repo).save(factu);
    }

    @Test
    void verTodas() {
        List<OrdenTrabajo> listas=new ArrayList<>(
                Arrays.asList(DatosDummy.getOrdenCreada(),
                        DatosDummy.getOrdenReparacion())
        );
        when(repo.findAll())
                .thenReturn(listas);
        List<OrdenTrabajo> ordenes=service.verTodas();

        assertThat(ordenes.size()).isEqualTo(2);
        verify(repo).findAll();
    }

    @Test
    void buscarByIdException() {
        OrdenTrabajo creada=DatosDummy.getOrdenCreada();
        when(repo.findById(creada.getId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->service.buscarById(creada.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("La orden de trabajo %d no existe ",
                        creada.getId()));
    }

    @Test
    void verByVehiculo() {
        String patente=DatosDummy.getVehiculoFiat().getPatente();
        OrdenTrabajo deFiat=DatosDummy.getOrdenCreada();
        OrdenTrabajo otraFiat=DatosDummy.getOrdenReparacion();
        List<OrdenTrabajo> listas=new ArrayList<>(
                Arrays.asList(deFiat,otraFiat)
        );
        when(repo.findAll())
                .thenReturn(listas);
        List<OrdenTrabajo> ordenes=service.verByVehiculo(patente);

        assertThat(ordenes.size()).isEqualTo(2);
        verify(repo).findAll();
        assertThat(ordenes.get(0).getVehiculo().getPatente()).isEqualTo(patente);
    }
}