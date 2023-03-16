package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Mecanico;
import com.besysoft.taller.repository.MecanicoRepository;
import com.besysoft.taller.service.interfaces.IMecanicoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    @Override
    public Mecanico buscarById(Long id) {
        Optional<Mecanico> oMe=this.repo.findById(id);
        if(oMe.isEmpty()){
            throw new NonExistingException(
                    String.format("El mecanico con id %d no existe ",
                            id)
            );
        }
        return oMe.get();
    }

    @Override
    public void addManoObra(Mecanico mecanico, ManoObra manoObra) {
        List<ManoObra> obras=mecanico.getListaManoObra();
        obras.add(manoObra);
        mecanico.setListaManoObra(obras);
    }
}
