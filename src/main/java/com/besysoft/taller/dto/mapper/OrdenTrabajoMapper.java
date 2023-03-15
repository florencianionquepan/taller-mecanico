package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.springframework.stereotype.Component;

@Component
public class OrdenTrabajoMapper implements IOrdenTrabajoMapper{

    private final IRecepMapper recepMapper;
    private final IAdminMapper adminMap;
    private final IVehiculoMapper vehiMap;

    public OrdenTrabajoMapper(IAdminMapper adminMap, IRecepMapper recepMapper, IVehiculoMapper vehiMap) {
        this.recepMapper = recepMapper;
        this.adminMap = adminMap;
        this.vehiMap = vehiMap;
    }

    @Override
    public OrdenTrabajoDTO mapToDto(OrdenTrabajo entidad) {
        OrdenTrabajoDTO dto=new OrdenTrabajoDTO();
        dto.setId(entidad.getId());
        dto.setNivelCombustible(entidad.getNivelCombustible());
        dto.setKilometraje(entidad.getKilometraje());
        dto.setFalla(entidad.getDetalleFalla());
        dto.setFechaIngreso(entidad.getFechaIngreso());
        dto.setFechaPago(entidad.getFechaPago());
        dto.setFormaPago(entidad.getFormaPago());
        dto.setCantidadCuotas(entidad.getCantidadCuotas());
        dto.setTipoTarjeta(entidad.getTipoTarjeta());
        dto.setImporteTotal(entidad.getImporteTotal());
        dto.setFechaFinReparacion(entidad.getFechaFinReparacion());
        if(entidad.getRecepcionista()!=null){
            dto.setRecepcionista(this.recepMapper.mapToDto(entidad.getRecepcionista()));
        }
        if(entidad.getAdministrativo()!=null){
            dto.setAdministrativo(this.adminMap.mapToDto(entidad.getAdministrativo()));
        }
        if(entidad.getVehiculo()!=null){
            dto.setVehiculo(this.vehiMap.mapToDto(entidad.getVehiculo()));
        }
        return dto;
    }

    @Override
    public OrdenTrabajo mapToEntity(OrdenTrabajoDTO dto) {
        OrdenTrabajo enti=new OrdenTrabajo();
        enti.setId(dto.getId());
        enti.setNivelCombustible(dto.getNivelCombustible());
        enti.setKilometraje(dto.getKilometraje());
        enti.setDetalleFalla(dto.getFalla());
        enti.setFechaIngreso(dto.getFechaIngreso());
        enti.setFechaPago(dto.getFechaPago());
        enti.setFormaPago(dto.getFormaPago());
        enti.setCantidadCuotas(dto.getCantidadCuotas());
        enti.setTipoTarjeta(dto.getTipoTarjeta());
        enti.setImporteTotal(dto.getImporteTotal());
        enti.setFechaFinReparacion(dto.getFechaFinReparacion());
        if(dto.getRecepcionista()!=null){
            enti.setRecepcionista(this.recepMapper.mapToEntity(dto.getRecepcionista()));
        }
        if(dto.getAdministrativo()!=null){
            enti.setAdministrativo(this.adminMap.mapToEntity(dto.getAdministrativo()));
        }
        if(dto.getVehiculo()!=null){
            enti.setVehiculo(this.vehiMap.mapToEntity(dto.getVehiculo()));
        }
        return enti;
    }
}
