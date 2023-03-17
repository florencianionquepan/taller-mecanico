package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.Repuesto;
import com.besysoft.taller.repository.RepuestoRepository;
import com.besysoft.taller.service.interfaces.IRepuestoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImple implements IRepuestoService {

    private final RepuestoRepository repo;

    public RepuestoServiceImple(RepuestoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Repuesto altaRepuesto(Repuesto repuesto) {
        return this.repo.save(repuesto);
    }

    @Override
    public List<Repuesto> verRepuestos() {
        return (List<Repuesto>) this.repo.findAll();
    }
}
