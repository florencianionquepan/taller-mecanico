package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ManoObraDTO;
import com.besysoft.taller.model.ManoObra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IManoObraMecanicoMapper {
    ManoObra mapToEntity(ManoObraDTO dto);
    ManoObraDTO mapToDto(ManoObra entidad);
    List<ManoObra> mapListToEntities(List<ManoObraDTO> dtos);
    List<ManoObraDTO> mapListToDto(List<ManoObra> entities);
}
