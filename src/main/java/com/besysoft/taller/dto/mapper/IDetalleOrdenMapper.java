package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.DetalleOrdenDTO;
import com.besysoft.taller.model.DetalleOrdenTrabajo;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IDetalleOrdenMapper {
    DetalleOrdenTrabajo mapToEntity(DetalleOrdenDTO dto);
    DetalleOrdenDTO mapToDto(DetalleOrdenTrabajo entidad);
    List<DetalleOrdenTrabajo> mapListToEntities(List<DetalleOrdenDTO> dtos);
    List<DetalleOrdenDTO> MapListToDto(List<DetalleOrdenTrabajo> entidades);

}
