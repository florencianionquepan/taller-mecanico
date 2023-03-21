package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.MissingDataException;
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
    //manoObra viene con el mecanico asignado y la orden
    public ManoObra altaManoObra(ManoObra manoObra) {
        return this.repo.save(manoObra);
    }

    @Override
    public ManoObra modiManoObra(ManoObra manoObra, Long id) {
        manoObra.setId(id);
        if(manoObra.getDetalle()==null || manoObra.getDuracionHs()==null
            || manoObra.getDetalle().isEmpty()){
            throw new MissingDataException(
                    "Debe completar el detalle y la duracion de tiempo en Hs"
            );
        }
        return this.repo.save(manoObra);
    }
}
