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
        //para traer el objeto entero busco por id a orden de trabajo y a mecanico
        //esto para que response de mano obra me muestre los atributos de estos objetos
        //guardarle al mecanico esta mano de obra
        Mecanico meca=this.mecaService.buscarById(manoObra.getMecanico().getId());
        this.mecaService.addManoObra(meca,manoObra);
        OrdenTrabajo orden=this.ordenService.buscarById(manoObra.getOrdenTrabajo().getId());
        //ordenTrabajo no posee mano_obra. relacion 1 a 1
        ManoObra guardada=this.repo.save(manoObra);
        return guardada;
    }

    @Override
    public ManoObra modiManoObra(ManoObra manoObra, Long id) {
        manoObra.setId(id);
        return this.repo.save(manoObra);
    }
}
