package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.DetalleOrdenDTO;
import com.besysoft.taller.model.DetalleOrdenTrabajo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetalleOrdenMapper implements IDetalleOrdenMapper{

    private final IRepuestoMapper repuMapper;

    public DetalleOrdenMapper(IRepuestoMapper repuMapper) {
        this.repuMapper = repuMapper;
    }

    @Override
    public DetalleOrdenTrabajo mapToEntity(DetalleOrdenDTO dto) {
        DetalleOrdenTrabajo detalle=new DetalleOrdenTrabajo();
        detalle.setId(dto.getId());
        detalle.setCantidad(dto.getCantidad());
        detalle.setRepuesto(this.repuMapper.mapToEntity(dto.getRepuesto()));
        return detalle;
    }

    @Override
    public DetalleOrdenDTO mapToDto(DetalleOrdenTrabajo entidad) {
        DetalleOrdenDTO dto=new DetalleOrdenDTO();
        dto.setId(entidad.getId());
        dto.setCantidad(entidad.getCantidad());
        dto.setValorTotal(entidad.getValorTotal());
        dto.setRepuesto(this.repuMapper.mapToDto(entidad.getRepuesto()));
        return dto;
    }

    @Override
    public List<DetalleOrdenTrabajo> mapListToEntities(List<DetalleOrdenDTO> dtos) {
        return dtos.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public List<DetalleOrdenDTO> MapListToDto(List<DetalleOrdenTrabajo> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
