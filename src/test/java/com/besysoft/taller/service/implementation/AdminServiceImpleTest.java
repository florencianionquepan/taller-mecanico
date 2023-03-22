package com.besysoft.taller.service.implementation;

import com.besysoft.taller.datos.DatosDummy;
import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.repository.AdminRepository;
import com.besysoft.taller.service.interfaces.IAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceImpleTest {

    private AdminRepository repo;
    private IAdminService service;

    @BeforeEach
    void setUp() {
        repo=mock(AdminRepository.class);
        service=new AdminServiceImple(repo);
    }

    @Test
    void altaAdmin() {
        //GIVEN
        Administrativo admin= DatosDummy.getAdmin();
        admin.setPersona(DatosDummy.getPersonaUno());
        when(repo.save(admin))
                .thenReturn(admin);
        //WHEN
        service.altaAdmin(admin);

        //THEN
        ArgumentCaptor<Administrativo> adminArgumentCaptor=ArgumentCaptor.forClass(Administrativo.class);
        verify(repo).save(adminArgumentCaptor.capture());

        Administrativo adminCaptor=adminArgumentCaptor.getValue();
        assertThat(adminCaptor).isEqualTo(admin);
    }

    @Test
    void verAdmin() {
        Administrativo admin= DatosDummy.getAdmin();
        admin.setPersona(DatosDummy.getPersonaUno());
        when(repo.findAll())
                .thenReturn(new ArrayList<Administrativo>(
                        List.of(admin)
                ));

        List<Administrativo> adminis=service.verAdmin();

        assertThat(adminis.size()).isEqualTo(1);
        verify(repo).findAll();
    }

    @Test
    void buscarById() {
        //GIVEN
        Administrativo admin= DatosDummy.getAdmin();
        admin.setPersona(DatosDummy.getPersonaUno());
        when(repo.save(admin))
                .thenReturn(admin);
        when(repo.findById(anyLong()))
                .thenReturn(Optional.of(admin));

        //WHEN
        Administrativo adminExpected=service.buscarById(anyLong());

        //THEN
        assertThat(adminExpected).isEqualTo(admin);
        verify(repo).findById(anyLong());
    }

    @Test
    void buscarByIdException() {
        //GIVEN
        Administrativo admin= DatosDummy.getAdmin();
        admin.setPersona(DatosDummy.getPersonaUno());
        when(repo.findById(admin.getId()))
                .thenReturn(Optional.empty());

        //WHEN
        //THEN
        assertThatThrownBy(()->service.buscarById(admin.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(String.format("El administrativo con id %d no existe",
                        admin.getId()));
    }
}