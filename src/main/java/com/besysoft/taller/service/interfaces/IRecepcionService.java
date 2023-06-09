package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.Recepcionista;

import java.util.List;

public interface IRecepcionService {
    Recepcionista altaRecepcion(Recepcionista recep);
    List<Recepcionista> verTodas();
    Recepcionista buscarById(Long id);
}
