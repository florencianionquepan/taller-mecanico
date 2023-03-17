package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.RepuestoDTO;
import com.besysoft.taller.model.Repuesto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRepuestoMapper {
    Repuesto mapToEntity(RepuestoDTO dto);
    RepuestoDTO mapToDto(Repuesto entidad);
    List<RepuestoDTO> mapListToDto(List<Repuesto> entidades);
}
