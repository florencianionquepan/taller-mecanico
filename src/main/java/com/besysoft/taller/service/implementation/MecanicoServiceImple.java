package com.besysoft.taller.service.implementation;

import com.besysoft.taller.controller.OrdenTrabajoController;
import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.EstadoOrden;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Mecanico;
import com.besysoft.taller.repository.MecanicoRepository;
import com.besysoft.taller.service.interfaces.IMecanicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MecanicoServiceImple implements IMecanicoService {

    private final MecanicoRepository repo;
    private Logger logger= LoggerFactory.getLogger(MecanicoServiceImple.class);

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


    //El metodo me retorna el mecanico activo con menor cantidad de mano de obra
    @Override
    public Mecanico mecanicoConMenosObras(){
        List<Mecanico> activos=((List<Mecanico>) this.repo.findAll()).stream()
                        .filter(meca->meca.getActivo().toString().equals("T"))
                        .collect(Collectors.toList());
        Mecanico mecaMenosObras=activos.stream()
                .min(Comparator.comparingInt(mecanico ->
                        cantidadObrasVigentes(mecanico.getListaManoObra()))
                ).orElse(null);
        logger.info("Mecanico con menos obras"+mecaMenosObras);
        return mecaMenosObras;
    }

    private Integer cantidadObrasVigentes(List<ManoObra> manoObras){
        Integer manoObrasCreadas= (int) manoObras.stream()
                .filter(obra -> obra.getOrdenTrabajo().getEstado().equals(EstadoOrden.CREADA))
                .count();
        Integer manosObrasReparacion= (int) manoObras.stream()
                .filter(obra -> obra.getOrdenTrabajo().getEstado().equals(EstadoOrden.REPARACION))
                .count();
        return manoObrasCreadas+manosObrasReparacion;
    }
}
