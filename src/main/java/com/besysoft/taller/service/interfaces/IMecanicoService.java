package com.besysoft.taller.service.interfaces;

import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Mecanico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMecanicoService {

    Mecanico altaMecanico(Mecanico mecanico);
    List<Mecanico> verActivos();
    Mecanico buscarById(Long id);
    Mecanico mecanicoConMenosObras();
    void addManoObra(Mecanico mecanico, ManoObra manoObra);
}
