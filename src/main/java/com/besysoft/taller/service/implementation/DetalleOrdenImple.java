package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.DetalleOrdenTrabajo;
import com.besysoft.taller.repository.DetalleOrdenRepository;
import com.besysoft.taller.service.interfaces.IDetalleOrdenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenImple implements IDetalleOrdenService {

    private final DetalleOrdenRepository repo;

    public DetalleOrdenImple(DetalleOrdenRepository repo) {
        this.repo = repo;
    }

    @Override
    public DetalleOrdenTrabajo altaDetalleOrden(DetalleOrdenTrabajo detalle) {
        //chequear que el repuesto exista inyectando serviceRepuesto
        //chequear que cantidad>1 en DTO lo puedo hacer
        //calcular valor total
        return this.repo.save(detalle);
    }
}
