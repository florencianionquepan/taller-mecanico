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
        dto.setFormaPago(entidad.getFormaPago());
        dto.setCantidadCuotas(entidad.getCantidadCuotas());
        dto.setTipoTarjeta(entidad.getTipoTarjeta());
        //falta recepDTO
        dto.setRecepcionista(entidad.getRecepcionista());
        dto.setAdministrativo(this.AdminMap.mapToDto(entidad.getAdministrativo()));
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
        enti.setFormaPago(dto.getFormaPago());
        enti.setCantidadCuotas(dto.getCantidadCuotas());
        enti.setTipoTarjeta(dto.getTipoTarjeta());
        enti.setRecepcionista(dto.getRecepcionista());
        enti.setAdministrativo(this.AdminMap.mapToEntity(dto.getAdministrativo()));
        enti.setVehiculo(this.vehiMap.mapToEntity(dto.getVehiculo()));
        return enti;
    }
}
