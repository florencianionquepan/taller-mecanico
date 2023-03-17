package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.EstadoOrden;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.IManoObraService;
import com.besysoft.taller.service.interfaces.IOrdenService;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImple implements IOrdenService {

    private final OrdenTrabajoRepository repo;
    private final IRecepcionService recepService;

    public OrdenServiceImple(OrdenTrabajoRepository repo, IRecepcionService recepService) {
        this.repo = repo;
        this.recepService = recepService;
    }

    @Override
    public OrdenTrabajo altaOrden(OrdenTrabajo orden) {
        if(!this.recepService.existeRecepcionista(orden.getRecepcionista())){
            throw new NonExistingException(
                    String.format("La recepcionista %s no existe",
                            orden.getRecepcionista().getPersona().getNombres()
                    )
            );
        }
        orden.setEstado(EstadoOrden.CREADA);
        Long datetime = System.currentTimeMillis();
        Timestamp fechaIn = new Timestamp(datetime);
        orden.setFechaIngreso(fechaIn);
        OrdenTrabajo nueva=this.repo.save(orden);
        return nueva;
    }

    @Override
    public OrdenTrabajo modiOrden(OrdenTrabajo orden) {
        return null;
    }

    @Override
    public List<OrdenTrabajo> verTodas() {
        return (List<OrdenTrabajo>) this.repo.findAll();
    }

    @Override
    public OrdenTrabajo buscarById(Long id) {
        Optional<OrdenTrabajo> oEstado=this.repo.findById(id);
        if(oEstado.isEmpty()){
            throw new NonExistingException(
                    String.format("La orden de trabajo %d no existe ",
                            id)
            );
        }
        return oEstado.get();
    }

    @Override
    public void addManoObra(OrdenTrabajo orden, ManoObra obra) {
        List<ManoObra> obras=orden.getListaManoObra();
        obras.add(obra);
        orden.setListaManoObra(obras);
    }
}
