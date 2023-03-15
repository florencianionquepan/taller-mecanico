package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.Recepcionista;
import com.besysoft.taller.repository.RecepcionRepository;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecepcionServiceImple implements IRecepcionService {

    private final RecepcionRepository repo;

    public RecepcionServiceImple(RecepcionRepository repo) {
        this.repo = repo;
    }

    @Override
    public Recepcionista altaRecepcion(Recepcionista recep) {
        return this.repo.save(recep);
    }

    @Override
    public List<Recepcionista> verTodas() {
        return (List<Recepcionista>) this.repo.findAll();
    }
}
