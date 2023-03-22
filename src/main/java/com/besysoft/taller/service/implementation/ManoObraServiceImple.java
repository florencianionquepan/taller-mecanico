package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.service.interfaces.IManoObraService;
import com.besysoft.taller.service.interfaces.IMecanicoService;
import org.springframework.stereotype.Service;

@Service
public class ManoObraServiceImple implements IManoObraService {

    private final ManoObraRepository repo;
    private final IMecanicoService mecaService;

    public ManoObraServiceImple(ManoObraRepository repo, IMecanicoService mecaService) {
        this.repo = repo;
        this.mecaService = mecaService;
    }

    @Override
    //manoObra viene con el mecanico asignado y la orden
    public ManoObra altaManoObra(ManoObra manoObra) {
        return this.repo.save(manoObra);
    }

}
