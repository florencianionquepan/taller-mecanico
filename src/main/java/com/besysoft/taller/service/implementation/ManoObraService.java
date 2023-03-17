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

    public ManoObraService(ManoObraRepository repo, IMecanicoService mecaService) {
        this.repo = repo;
        this.mecaService = mecaService;
    }

    @Override
    public ManoObra altaManoObra(ManoObra manoObra) {
        //el mecanico se selecciona de forma automatica
        Mecanico meca=this.mecaService.mecanicoConMenosObras();
        //guardarle al mecanico esta mano de obra
        this.mecaService.addManoObra(meca,manoObra);
        //ordenTrabajo no posee mano_obra. relacion 1 a 1
        manoObra.setMecanico(meca);
        ManoObra guardada=this.repo.save(manoObra);
        return guardada;
    }

    @Override
    public ManoObra modiManoObra(ManoObra manoObra, Long id) {
        manoObra.setId(id);
        return this.repo.save(manoObra);
    }
}
