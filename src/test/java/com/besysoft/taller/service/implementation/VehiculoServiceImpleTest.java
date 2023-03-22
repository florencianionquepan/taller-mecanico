package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.repository.VehiculoRepository;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehiculoServiceImpleTest {

    private VehiculoRepository repo;
    private IVehiculoService service;

    @BeforeEach
    void setUp() {
        repo=mock(VehiculoRepository.class);
        service=new VehiculoServiceImple(repo);
    }

    @Test
    void altaVehiculo() {
        Vehiculo vehi= DatosDummy.getVehiculoRenault();
        when(repo.save(vehi))
                .thenReturn(vehi);

        service.altaVehiculo(vehi);

        ArgumentCaptor<Vehiculo> vehiArgumentCaptor=ArgumentCaptor.forClass(Vehiculo.class);
        verify(repo).save(vehiArgumentCaptor.capture());

        Vehiculo vehiCaptor=vehiArgumentCaptor.getValue();
        assertThat(vehiCaptor).isEqualTo(vehi);
    }

    @Test
    void altaVehiculoException() {
        Vehiculo vehi= DatosDummy.getVehiculoRenault();
        when(repo.buscarPorPatente(vehi.getPatente()))
                .thenReturn(Optional.of(vehi));

        assertThatThrownBy(()->service.altaVehiculo(vehi))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El vehiculo con patente %s ya existe ",
                        vehi.getPatente()));
    }

    @Test
    void existeVehiculo() {
        Vehiculo vehi= DatosDummy.getVehiculoRenault();
        when(repo.save(vehi))
                .thenReturn(vehi);
        when(repo.buscarPorPatente(vehi.getPatente()))
                .thenReturn(Optional.of(vehi));
        //WHEN
        //THEN
        assertThat(service.existeVehiculo(vehi.getPatente())).isTrue();
        verify(repo).buscarPorPatente(any());
    }

    @Test
    void buscarPorPatenteException() {
        Vehiculo vehi= DatosDummy.getVehiculoRenault();
        when(repo.buscarPorPatente(vehi.getPatente()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->service.buscarPorPatente(vehi.getPatente()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El vehiculo con patente %s no existe ",
                        vehi.getPatente()));
    }

    @Test
    void addClienteVehiculo() {
        Vehiculo vehi= DatosDummy.getVehiculoRenault();
        Cliente igorio=DatosDummy.getClienteIgor();
        when(repo.save(any()))
                .thenReturn(vehi);
        //WHEN
        service.addClienteVehiculo(vehi,igorio);
        //THEN
        boolean result=vehi.getListaClientes().contains(igorio);
    }
}