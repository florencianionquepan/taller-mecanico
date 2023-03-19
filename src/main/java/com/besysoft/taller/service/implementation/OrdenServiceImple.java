package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.*;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.IDetalleOrdenService;
import com.besysoft.taller.service.interfaces.IOrdenService;
import com.besysoft.taller.service.interfaces.IRecepcionService;
import com.besysoft.taller.service.interfaces.IVehiculoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImple implements IOrdenService {

    private final OrdenTrabajoRepository repo;
    private final IRecepcionService recepService;
    private final IVehiculoService vehiService;
    private final ManoObraRepository manoObraRepo;
    private final IDetalleOrdenService detalleOrdenService;

    public OrdenServiceImple(OrdenTrabajoRepository repo,
                             IRecepcionService recepService,
                             IVehiculoService vehiService,
                             ManoObraRepository manoObraRepo,
                             IDetalleOrdenService detalleOrdenService) {
        this.repo = repo;
        this.recepService = recepService;
        this.vehiService = vehiService;
        this.manoObraRepo = manoObraRepo;
        this.detalleOrdenService = detalleOrdenService;
    }

    @Override
    public OrdenTrabajo altaOrden(OrdenTrabajo orden) {
        if(!this.recepService.existeRecepcionista(orden.getRecepcionista())){
            throw new NonExistingException(
                    String.format("La recepcionista con id %d no existe",
                            orden.getRecepcionista().getId()
                    )
            );
        }
        Vehiculo vehi=this.vehiService.buscarPorPatente(orden.getVehiculo().getPatente());
        orden.setVehiculo(vehi);
        orden.setEstado(EstadoOrden.CREADA);
        LocalDateTime fecha=LocalDateTime.now();
        orden.setFechaIngreso(fecha);
        OrdenTrabajo nueva=this.repo.save(orden);
        return nueva;
    }

    @Override
    public OrdenTrabajo iniciarReparacion(Long id) {
        OrdenTrabajo orden=this.buscarById(id);
        orden.setEstado(EstadoOrden.REPARACION);
        return this.repo.save(orden);
    }

    @Override
    @Transactional
    public OrdenTrabajo finalizarReparacion(Long id, OrdenTrabajo orden) {
        //se chequea que el id corresponda a una orden existente
        OrdenTrabajo ordenGuardada=this.buscarById(id);
        /*MANO OBRAS //el dto valida campos de mano obra */
        List<ManoObra> obras=orden.getListaManoObra();
        List<ManoObra> obrasActuales=this.completarObras(obras,id);
        this.updateObras(obrasActuales);
        ordenGuardada.setListaManoObra(obrasActuales);
        /*DETALLES ORDEN TRABAJO*/
        this.crearDetalles(orden,orden.getListaDetalleOrdenes());
        ordenGuardada.setListaDetalleOrdenes(orden.getListaDetalleOrdenes());
        //ORDEN TRABAJO ATRIBUTOS
        LocalDateTime fechaHoy=LocalDateTime.now();
        ordenGuardada.setFechaFinReparacion(fechaHoy);
        ordenGuardada.setEstado(EstadoOrden.AFACTURAR);
        return this.repo.save(ordenGuardada);
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

    //devuelve el listado de obras completo y chequea que los datos que trae sean correctos
    private List<ManoObra> completarObras(List<ManoObra> obras,Long id){
        List<ManoObra> manoObras=new ArrayList<ManoObra>();
        for(ManoObra obra: obras){
            Optional<ManoObra> obraBD=this.manoObraRepo.findById(obra.getId());
            if(obraBD.isEmpty()){
                throw new NonExistingException(
                        String.format("La mano de obra %d no existe ",
                                obra.getId())
                );
            }
            if(!obraBD.get().getOrdenTrabajo().getId().equals(id)){
                throw new NonExistingException(
                        String.format("La mano de obra %d no corresponde a la orden ",
                                obra.getId())
                );
            }
            obra.setOrdenTrabajo(obraBD.get().getOrdenTrabajo());
            obra.setMecanico(obraBD.get().getMecanico());
            manoObras.add(obra);
        }
        return manoObras;
    }

    private void updateObras(List<ManoObra> obras){
        //hacerle el save con el mecanico y la orden!!
        for(ManoObra obra:obras){
            this.manoObraRepo.save(obra);
        }
    }

    private void crearDetalles(OrdenTrabajo orden,List<DetalleOrdenTrabajo> detalles){
        for(DetalleOrdenTrabajo detalle:detalles){
            detalle.setOrdenTrabajo(orden);
            this.detalleOrdenService.altaDetalleOrden(detalle);
        }
    }
}
