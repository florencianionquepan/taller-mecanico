package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.Mecanico;
import com.besysoft.taller.repository.MecanicoRepository;
import com.besysoft.taller.service.interfaces.IMecanicoService;

import java.util.List;

public class MecanicoServiceImple implements IMecanicoService {

    private final MecanicoRepository repo;

    public MecanicoServiceImple(MecanicoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Mecanico altaMecanico(Mecanico mecanico) {
        return this.repo.save(mecanico);
    }

    @Override
    public List<Mecanico> verTodos() {
        return (List<Mecanico>) this.repo.findAll();
    }
}
