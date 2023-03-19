package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.OrdenFacturadaDTO;
import com.besysoft.taller.model.OrdenTrabajo;

public interface IOrdenFacturadaMapper {
    OrdenFacturadaDTO mapToDto(OrdenTrabajo enti);
    OrdenTrabajo mapToEntity(OrdenFacturadaDTO dto);
}
