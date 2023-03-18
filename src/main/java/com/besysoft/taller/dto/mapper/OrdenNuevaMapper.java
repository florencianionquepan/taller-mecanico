package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenNuevaDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdenNuevaMapper implements IOrdenNuevaMapper{

    private final IRecepMapper recepMapper;
    private final IVehiculoMapper vehiMap;

    public OrdenNuevaMapper(IRecepMapper recepMapper, IVehiculoMapper vehiMap) {
        this.recepMapper = recepMapper;
        this.vehiMap = vehiMap;
    }

    @Override
    public OrdenNuevaDTO mapToDto(OrdenTrabajo entidad) {
        OrdenNuevaDTO dto=new OrdenNuevaDTO();
        dto.setId(entidad.getId());
        dto.setEstado(entidad.getEstado());
        dto.setNivelCombustible(entidad.getNivelCombustible());
        dto.setKilometraje(entidad.getKilometraje());
        dto.setFalla(entidad.getDetalleFalla());
        dto.setFechaIngreso(entidad.getFechaIngreso());
        dto.setRecepcionista(this.recepMapper.mapToDto(entidad.getRecepcionista()));
        dto.setVehiculo(this.vehiMap.mapToDto(entidad.getVehiculo()));
        return dto;

    }

    @Override
    public OrdenTrabajo mapToEntity(OrdenNuevaDTO dto) {
        OrdenTrabajo enti=new OrdenTrabajo();
        enti.setId(dto.getId());
        enti.setNivelCombustible(dto.getNivelCombustible());
        enti.setKilometraje(dto.getKilometraje());
        enti.setDetalleFalla(dto.getFalla());
        enti.setFechaIngreso(dto.getFechaIngreso());
        enti.setRecepcionista(this.recepMapper.mapToEntity(dto.getRecepcionista()));
        enti.setVehiculo(this.vehiMap.mapToEntity(dto.getVehiculo()));
        return enti;
    }

    @Override
    public List<OrdenNuevaDTO> mapListToDto(List<OrdenTrabajo> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
