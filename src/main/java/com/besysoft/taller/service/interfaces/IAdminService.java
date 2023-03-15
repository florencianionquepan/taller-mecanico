package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.Administrativo;

import java.util.List;

public interface IAdminService {
    Administrativo altaAdmin(Administrativo admin);

    List<Administrativo> verAdmin();
}
