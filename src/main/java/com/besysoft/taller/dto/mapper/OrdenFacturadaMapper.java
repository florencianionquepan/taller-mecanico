package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenFacturadaDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.springframework.stereotype.Component;

@Component
public class OrdenFacturadaMapper implements IOrdenFacturadaMapper{

    private final IAdminMapper adminMap;
    private final IVehiculoClienteMapper vehiMap;

    public OrdenFacturadaMapper(IAdminMapper adminMap, IVehiculoClienteMapper vehiMap) {
        this.adminMap = adminMap;
        this.vehiMap = vehiMap;
    }

    @Override
    public OrdenFacturadaDTO mapToDto(OrdenTrabajo enti) {
        OrdenFacturadaDTO dto=new OrdenFacturadaDTO();
        dto.setId(enti.getId());
        dto.setEstado(enti.getEstado());
        dto.setFechaPago(enti.getFechaPago());
        dto.setFormaPago(enti.getFormaPago());
        dto.setCantidadCuotas(enti.getCantidadCuotas());
        dto.setTipoTarjeta(enti.getTipoTarjeta());
        dto.setImporteTotal(enti.getImporteTotal());
        dto.setAdministrativo(this.adminMap.mapToDto(enti.getAdministrativo()));
        dto.setVehiculo(this.vehiMap.mapToDto(enti.getVehiculo()));
        return dto;
    }

    @Override
    public OrdenTrabajo mapToEntity(OrdenFacturadaDTO dto) {
        OrdenTrabajo enti=new OrdenTrabajo();
        enti.setId(dto.getId());
        enti.setEstado(dto.getEstado());
        enti.setFechaPago(dto.getFechaPago());
        enti.setFormaPago(dto.getFormaPago());
        enti.setCantidadCuotas(dto.getCantidadCuotas());
        enti.setTipoTarjeta(dto.getTipoTarjeta());
        enti.setImporteTotal(dto.getImporteTotal());
        enti.setAdministrativo(this.adminMap.mapToEntity(dto.getAdministrativo()));
        return enti;
    }
}
