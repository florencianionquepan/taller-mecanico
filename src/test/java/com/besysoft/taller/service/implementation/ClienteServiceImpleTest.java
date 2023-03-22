package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Cliente;
import com.besysoft.taller.model.Vehiculo;
import com.besysoft.taller.repository.ClienteRepository;
import com.besysoft.taller.service.interfaces.IClienteService;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceImpleTest {

    private ClienteRepository repo;
    private IClienteService service;
    private IVehiculoService vehiService;

    @BeforeEach
    void setUp() {
        repo=mock(ClienteRepository.class);
        vehiService=mock(IVehiculoService.class);
        service=new ClienteServiceImple(repo, vehiService);
    }

    @Test
    void altaCliente() {
        //GIVEN
        Cliente emma= DatosDummy.getClienteEmma();
        when(repo.save(emma))
                .thenReturn(emma);
        //WHEN
        service.altaCliente(emma);
        //THEN
        ArgumentCaptor<Cliente> clienteArgumentCaptor=ArgumentCaptor.forClass(Cliente.class);
        verify(repo).save(clienteArgumentCaptor.capture());

        Cliente cliCaptor=clienteArgumentCaptor.getValue();
        assertThat(cliCaptor).isEqualTo(emma);
    }

    @Test
    void altaClienteException() {
        //GIVEN
        Cliente emma= DatosDummy.getClienteEmma();
        when(repo.buscarPorEmail(emma.getCorreoElectronico()))
                .thenReturn(Optional.of(emma));
        //WHEN
        //THEN
        assertThatThrownBy(()->service.altaCliente(emma))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El cliente con email %s ya se encuentra registrado ",
                        emma.getCorreoElectronico()));
    }

    @Test
    void recibeClienteYVehiculoNoExistente() {
        //GIVEN
        String email="emma@yahoo.com";
        String patente="AB123CD";
        when(vehiService.existeVehiculo(patente))
                .thenReturn(false);
        when(repo.buscarPorEmail(email))
                .thenReturn(Optional.empty());
        //WHEN
        //THEN
        assertThatThrownBy(()->service.recibeCliente(email,patente))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El vehiculo con patente %s no existe." +
                                "El cliente con email %s no se encuentra registrado.",
                        patente , email));
    }

    @Test
    void recibeVehiculoNoExistente() {
        //GIVEN
        Cliente emma=DatosDummy.getClienteEmma();
        String email="emma@yahoo.com";
        String patente="AB123CD";
        when(vehiService.existeVehiculo(patente))
                .thenReturn(false);
        when(repo.buscarPorEmail(email))
                .thenReturn(Optional.of(emma));
        //WHEN
        //THEN
        assertThatThrownBy(()->service.recibeCliente(email,patente))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El vehiculo con patente %s no existe ",
                        patente));
    }

    @Test
    void recibeClienteNoExistente() {
        //GIVEN
        String email="emma@yahoo.com";
        String patente="AB123CD";
        when(vehiService.existeVehiculo(patente))
                .thenReturn(true);
        when(repo.buscarPorEmail(email))
                .thenReturn(Optional.empty());
        //WHEN
        //THEN
        assertThatThrownBy(()->service.recibeCliente(email,patente))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El cliente con email %s no se encuentra registrado ",
                        email));
    }

    @Test
    void recibeClienteVinculado(){
        //GIVEN
        Cliente emma=DatosDummy.getClienteEmma();
        String email= emma.getCorreoElectronico();
        Vehiculo vehi=DatosDummy.getVehiculoRenault();
        String pate=vehi.getPatente();
        List<Vehiculo> vehiculos=emma.getListaVehiculos();
        vehiculos.add(vehi);
        when(vehiService.existeVehiculo(pate))
                .thenReturn(true);
        when(vehiService.buscarPorPatente(pate))
                .thenReturn(vehi);
        when(repo.save(emma))
                .thenReturn(emma);
        when(repo.buscarPorEmail(email))
                .thenReturn(Optional.of(emma));
        //THEN,WHEN
        assertThatThrownBy(()->service.recibeCliente(email,pate))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format(
                        "El cliente con email %s ya posee el vehiculo %s vinculado ",
                        email,pate));
    }

    @Test
    void recibeCliente(){
        //GIVEN
        Cliente emma=DatosDummy.getClienteEmma();
        String email= emma.getCorreoElectronico();
        Vehiculo vehi=DatosDummy.getVehiculoRenault();
        String pate=vehi.getPatente();
        when(vehiService.existeVehiculo(pate))
                .thenReturn(true);
        when(vehiService.buscarPorPatente(pate))
                .thenReturn(vehi);
        when(repo.buscarPorEmail(email))
                .thenReturn(Optional.of(emma));
        //WHEN
        service.recibeCliente(email,pate);
        //THEN
        assertThat(emma.getListaVehiculos().contains(vehi)).isTrue();
        verify(vehiService).addClienteVehiculo(vehi,emma);
        verify(repo).save(emma);
    }
}