package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.mapstruct.Mapper;

import java.util.List;

public interface IOrdenTrabajoMapper {
    OrdenTrabajoDTO mapToDto(OrdenTrabajo entidad);
    OrdenTrabajo mapToEntity(OrdenTrabajoDTO dto);
    List<OrdenTrabajoDTO> mapListToDto(List<OrdenTrabajo> entidades);
}
