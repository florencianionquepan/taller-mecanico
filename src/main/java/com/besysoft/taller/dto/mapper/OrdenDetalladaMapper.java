package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenDetalladaDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdenDetalladaMapper implements IOrdenDetalladaMapper {

    private final IVehiculoMapper vehiMap;
    private final IManoObraMapper obraMap;
    private final IDetalleOrdenMapper detalleOrdenMap;

    public OrdenDetalladaMapper(IVehiculoMapper vehiMap, IManoObraMapper obraMap, IDetalleOrdenMapper detalleOrdenMap) {
        this.vehiMap = vehiMap;
        this.obraMap = obraMap;
        this.detalleOrdenMap = detalleOrdenMap;
    }


    @Override
    public OrdenDetalladaDTO mapToDto(OrdenTrabajo entidad) {
        OrdenDetalladaDTO dto=new OrdenDetalladaDTO();
        dto.setId(entidad.getId());
        dto.setEstado(entidad.getEstado());
        dto.setFechaFinReparacion(entidad.getFechaFinReparacion());
        dto.setVehiculo(this.vehiMap.mapToDto(entidad.getVehiculo()));
        dto.setListaManoObra(this.obraMap.mapListToDto(entidad.getListaManoObra()));
        dto.setDetalleOrdenes(this.detalleOrdenMap.MapListToDto(entidad.getListaDetalleOrdenes()));
        return dto;
    }

    @Override
    public OrdenTrabajo mapToEntity(OrdenDetalladaDTO dto) {
        OrdenTrabajo enti=new OrdenTrabajo();
        enti.setId(dto.getId());
        enti.setEstado(dto.getEstado());
        enti.setFechaFinReparacion(dto.getFechaFinReparacion());
        enti.setVehiculo(this.vehiMap.mapToEntity(dto.getVehiculo()));
        enti.setListaManoObra(this.obraMap.mapListToEntities(dto.getListaManoObra()));
        enti.setListaDetalleOrdenes(this.detalleOrdenMap.mapListToEntities(dto.getDetalleOrdenes()));
        return enti;
    }

    @Override
    public List<OrdenDetalladaDTO> mapListToDto(List<OrdenTrabajo> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
