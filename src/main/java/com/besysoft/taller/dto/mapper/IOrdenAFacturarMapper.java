package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenAFacturarDTO;
import com.besysoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface IOrdenAFacturarMapper {
    OrdenAFacturarDTO mapToDto(OrdenTrabajo entidad);
    OrdenTrabajo mapToEntity(OrdenAFacturarDTO dto);
    List<OrdenAFacturarDTO> mapListToDto(List<OrdenTrabajo> entidades);
}
