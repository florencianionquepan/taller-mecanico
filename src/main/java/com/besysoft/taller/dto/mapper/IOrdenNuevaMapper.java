package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenNuevaDTO;
import com.besysoft.taller.model.OrdenTrabajo;

import java.util.List;

public interface IOrdenNuevaMapper {
    OrdenNuevaDTO mapToDto(OrdenTrabajo entidad);
    OrdenTrabajo mapToEntity(OrdenNuevaDTO dto);
    List<OrdenNuevaDTO> mapListToDto(List<OrdenTrabajo> entidades);
}
