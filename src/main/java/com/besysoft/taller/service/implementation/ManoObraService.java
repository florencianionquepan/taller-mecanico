package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Mecanico;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.service.interfaces.IManoObraService;
import com.besysoft.taller.service.interfaces.IMecanicoService;
import com.besysoft.taller.service.interfaces.IOrdenService;
import org.springframework.stereotype.Service;

@Service
public class ManoObraService implements IManoObraService {

    private final ManoObraRepository repo;
    private final IMecanicoService mecaService;
    private final IOrdenService ordenService;

    public ManoObraService(ManoObraRepository repo, IMecanicoService mecaService, IOrdenService ordenService) {
        this.repo = repo;
        this.mecaService = mecaService;
        this.ordenService = ordenService;
    }

    @Override
    public ManoObra altaManoObra(ManoObra manoObra) {
        //al buscarse se chequean que existan
        Mecanico meca=this.mecaService.buscarById(manoObra.getMecanico().getId());
        OrdenTrabajo orden=this.ordenService.buscarById(manoObra.getOrdenTrabajo().getId());
        //agregarle al mecanico y a orden esta mano de obra
        this.mecaService.addManoObra(meca,manoObra);
        this.ordenService.addManoObra(orden,manoObra);
        //manoObra.setMecanico(meca);
        return this.repo.save(manoObra);
    }

    @Override
    public ManoObra modiManoObra(ManoObra manoObra, Long id) {
        manoObra.setId(id);
        return this.repo.save(manoObra);
    }
}
