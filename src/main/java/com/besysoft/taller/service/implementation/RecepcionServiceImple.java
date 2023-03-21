package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.Recepcionista;
import com.besysoft.taller.repository.RecepcionRepository;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean existeRecepcionista(Recepcionista recep){
        return this.repo.existsById(recep.getId());
    }

    @Override
    public Recepcionista buscarById(Long id) {
        Optional<Recepcionista> oRecep=this.repo.findById(id);
        if(oRecep.isEmpty()){
            throw new NonExistingException(
                    String.format("La recepcionista con id %d no existe",
                            id
                    )
            );
        }
        return oRecep.get();
    }

}
