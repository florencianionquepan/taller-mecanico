package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.DetalleOrdenTrabajo;
import com.besysoft.taller.model.Repuesto;
import com.besysoft.taller.repository.DetalleOrdenRepository;
import com.besysoft.taller.service.interfaces.IDetalleOrdenService;
import com.besysoft.taller.service.interfaces.IRepuestoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DetalleOrdenImple implements IDetalleOrdenService {

    private final DetalleOrdenRepository repo;
    private final IRepuestoService repuService;

    public DetalleOrdenImple(DetalleOrdenRepository repo, IRepuestoService repuService) {
        this.repo = repo;
        this.repuService = repuService;
    }

    @Override
    public DetalleOrdenTrabajo altaDetalleOrden(DetalleOrdenTrabajo detalle) {
        Repuesto repuesto=this.repuService.buscarById(detalle.getRepuesto().getId());
        //cantidad>1 en DTO
        detalle.setValorTotal(repuesto.getValor().multiply(
                BigDecimal.valueOf(detalle.getCantidad())));
        return this.repo.save(detalle);
    }
}
