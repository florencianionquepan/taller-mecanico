package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.MissingDataException;
import com.besysoft.taller.exception.MissingStateException;
import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.*;
import com.besysoft.taller.repository.ManoObraRepository;
import com.besysoft.taller.repository.OrdenTrabajoRepository;
import com.besysoft.taller.service.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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
    private final IAdminService adminService;
    private final IMecanicoService mecaService;
    private final IManoObraService obraService;
    private Logger logger= LoggerFactory.getLogger(OrdenServiceImple.class);

    public OrdenServiceImple(OrdenTrabajoRepository repo,
                             IRecepcionService recepService,
                             IVehiculoService vehiService,
                             ManoObraRepository manoObraRepo,
                             IDetalleOrdenService detalleOrdenService,
                             IAdminService adminService,
                             IMecanicoService mecaService,
                             IManoObraService obraService) {
        this.repo = repo;
        this.recepService = recepService;
        this.vehiService = vehiService;
        this.manoObraRepo = manoObraRepo;
        this.detalleOrdenService = detalleOrdenService;
        this.adminService = adminService;
        this.mecaService = mecaService;
        this.obraService = obraService;
    }

    @Override
    public OrdenTrabajo altaOrden(OrdenTrabajo orden) {
        Recepcionista recep=this.recepService.buscarById(orden.getRecepcionista().getId());
        Vehiculo vehi=this.vehiService.buscarPorPatente(orden.getVehiculo().getPatente());
        orden.setRecepcionista(recep);
        orden.setVehiculo(vehi);
        orden.setEstado(EstadoOrden.CREADA);
        LocalDateTime fecha=LocalDateTime.now();
        orden.setFechaIngreso(fecha);
        OrdenTrabajo nueva=this.repo.save(orden);
        return nueva;
    }

    @Override
    @Transactional
    public OrdenTrabajo altaManoObra(Long id, ManoObra obra) {
        OrdenTrabajo ordenGuardada=this.buscarById(id);
        Mecanico mecaGuar=this.mecaService.buscarById(obra.getMecanico().getId());
        ManoObra nuevaMO=new ManoObra();
        nuevaMO.setMecanico(mecaGuar);
        nuevaMO.setOrdenTrabajo(ordenGuardada);
        ManoObra altaMO=this.obraService.altaManoObra(nuevaMO);
        this.mecaService.addManoObra(mecaGuar,altaMO);
        return ordenGuardada;
    }

    @Override
    public OrdenTrabajo iniciarReparacion(Long id) {
        OrdenTrabajo orden=this.buscarById(id);
        if(!orden.getEstado().equals(EstadoOrden.CREADA)){
            throw new MissingStateException(
                    String.format("La orden esta en estado %s" +
                                    ".No puede iniciar la reparacion de esta orden"
                            ,orden.getEstado()
                    )
            );
        }
        if(orden.getListaManoObra().size()==0){
            throw new MissingDataException(
                    String.format("La orden no posee mano de obra" +
                                    ".Debe generarla para luego iniciar la reparacion"
                    )
            );
        }
        orden.setEstado(EstadoOrden.REPARACION);
        return this.repo.save(orden);
    }

    @Override
    @Transactional
    public OrdenTrabajo finalizarReparacion(Long id, OrdenTrabajo orden) {
        OrdenTrabajo ordenGuardada=this.buscarById(id);
        if(!ordenGuardada.getEstado().equals(EstadoOrden.REPARACION)){
            throw new MissingStateException(
                    String.format("La orden esta en estado %s" +
                                    ".No puede finalizar la reparacion de esta orden"
                            ,ordenGuardada.getEstado()
                    )
            );
        }
        /* MANO OBRAS el dto valida campos de mano obra */
        List<ManoObra> obras=orden.getListaManoObra();
        List<ManoObra> obrasActuales=this.completarObras(obras,id);
        this.updateObras(obrasActuales);
        ordenGuardada.setListaManoObra(obrasActuales);
        /* DETALLES ORDEN TRABAJO */
        this.crearDetalles(ordenGuardada,orden.getListaDetalleOrdenes());
        ordenGuardada.setListaDetalleOrdenes(orden.getListaDetalleOrdenes());
        //ORDEN TRABAJO ATRIBUTOS
        LocalDateTime fechaHoy=LocalDateTime.now();
        ordenGuardada.setFechaFinReparacion(fechaHoy);
        ordenGuardada.setEstado(EstadoOrden.AFACTURAR);
        BigDecimal valorTotal=this.importeTotal(ordenGuardada);
        ordenGuardada.setImporteTotal(valorTotal);
        return this.repo.save(ordenGuardada);
    }

    @Override
    public OrdenTrabajo facturarOrden(Long id, OrdenTrabajo orden) {
        OrdenTrabajo ordenGuardada=this.buscarById(id);
        if(!ordenGuardada.getEstado().equals(EstadoOrden.AFACTURAR)){
            throw new MissingStateException(
                    String.format("La orden esta en estado %s" +
                                    ".No puede facturar ni cobrar esta orden"
                            ,ordenGuardada.getEstado()
                    )
            );
        }
        Administrativo admin=this.adminService.buscarById(orden.getAdministrativo().getId());
        ordenGuardada.setAdministrativo(admin);
        /* Calcule importe total al finalizar reparacion */
        ordenGuardada.setEstado(EstadoOrden.FACTURADA);
        ordenGuardada.setFechaPago(LocalDateTime.now());
        ordenGuardada.setFormaPago(orden.getFormaPago());
        ordenGuardada.setTipoTarjeta(orden.getTipoTarjeta());
        ordenGuardada.setCantidadCuotas(orden.getCantidadCuotas());
        return this.repo.save(ordenGuardada);
    }

    @Override
    public OrdenTrabajo cerrarOrden(Long id) {
        OrdenTrabajo ordenGuardada=this.buscarById(id);
        if(!ordenGuardada.getEstado().equals(EstadoOrden.FACTURADA)){
            throw new MissingStateException(
                    String.format("La orden esta en estado %s," +
                                    " debe ser facturada antes de cerrarse"
                    ,ordenGuardada.getEstado()
                    )
            );
        }
        ordenGuardada.setEstado(EstadoOrden.CERRADA);
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

    private BigDecimal importeTotal(OrdenTrabajo orden){
        List<DetalleOrdenTrabajo> detalles=orden.getListaDetalleOrdenes();
        BigDecimal total= BigDecimal.valueOf(0);
        total=detalles.stream()
                .map(DetalleOrdenTrabajo::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.info("TOTAL",total);
        return total;
    }
}
