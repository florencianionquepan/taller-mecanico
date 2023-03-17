package com.besysoft.taller.dto.mapper;


import com.besysoft.taller.dto.ManoObraRespDTO;
import com.besysoft.taller.model.ManoObra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IManoObraRespMapper {
    ManoObra mapToEntity(ManoObraRespDTO dto);
    ManoObraRespDTO mapToDto(ManoObra entidad);
    List<ManoObraRespDTO> mapListToDto(List<ManoObra> entidades);
}
