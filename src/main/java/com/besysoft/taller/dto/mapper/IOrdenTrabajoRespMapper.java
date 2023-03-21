package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenTrabajoRespDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrdenTrabajoRespMapper {
    OrdenTrabajoRespDTO mapToDto(OrdenTrabajo entidad);
    OrdenTrabajo mapToEntity(OrdenTrabajoRespDTO dto);
    List<OrdenTrabajoRespDTO> mapListToDto(List<OrdenTrabajo> entidades);
}
