package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenDetalladaDTO;
import com.besysoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface IOrdenDetalladaMapper {
    OrdenDetalladaDTO mapToDto(OrdenTrabajo entidad);
    OrdenTrabajo mapToEntity(OrdenDetalladaDTO dto);
    List<OrdenDetalladaDTO> mapListToDto(List<OrdenTrabajo> entidades);
}
