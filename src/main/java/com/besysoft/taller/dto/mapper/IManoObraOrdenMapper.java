package com.besysoft.taller.dto.mapper;


import com.besysoft.taller.dto.ManoObraOrdenDTO;
import com.besysoft.taller.model.ManoObra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IManoObraOrdenMapper {
    ManoObra mapToEntity(ManoObraOrdenDTO dto);
    ManoObraOrdenDTO mapToDto(ManoObra entidad);
    List<ManoObraOrdenDTO> mapListToDto(List<ManoObra> entidades);
}
