package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.springframework.stereotype.Component;

@Component
public class OrdenTrabajoMapper implements IOrdenTrabajoMapper{

    private final IAdminMapper AdminMap;
    private final IVehiculoMapper vehiMap;

    public OrdenTrabajoMapper(IAdminMapper adminMap, IVehiculoMapper vehiMap) {
        AdminMap = adminMap;
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
        //falta recepDTO
        dto.setRecepcionista(entidad.getRecepcionista());
        if(entidad.getAdministrativo()!=null){
            dto.setAdministrativo(this.AdminMap.mapToDto(entidad.getAdministrativo()));
        }
        dto.setVehiculo(this.vehiMap.mapToDto(entidad.getVehiculo()));
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
        enti.setRecepcionista(dto.getRecepcionista());
        if(dto.getAdministrativo()!=null){
            enti.setAdministrativo(this.AdminMap.mapToEntity(dto.getAdministrativo()));
        }
        enti.setVehiculo(this.vehiMap.mapToEntity(dto.getVehiculo()));
        return enti;
    }
}
