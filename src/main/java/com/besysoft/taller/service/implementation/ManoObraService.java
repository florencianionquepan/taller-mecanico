package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.service.interfaces.IManoObraService;
import org.springframework.stereotype.Service;

@Service
public class ManoObraService implements IManoObraService {

    private final ManoObraRepository repo;

    public ManoObraService(ManoObraRepository repo) {
        this.repo = repo;
    }

    @Override
    public ManoObra altaManoObra(ManoObra manoObra) {
        return this.repo.save(manoObra);
    }

    @Override
    public ManoObra modiManoObra(ManoObra manoObra, Long id) {
        manoObra.setId(id);
        return this.repo.save(manoObra);
    }
}
