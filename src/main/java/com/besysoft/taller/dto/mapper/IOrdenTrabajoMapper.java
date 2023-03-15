package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenTrabajoDTO;
import com.besysoft.taller.model.OrdenTrabajo;
import org.mapstruct.Mapper;

public interface IOrdenTrabajoMapper {
    OrdenTrabajoDTO mapToDto(OrdenTrabajo entidad);
    OrdenTrabajo mapToEntity(OrdenTrabajoDTO dto);
}
