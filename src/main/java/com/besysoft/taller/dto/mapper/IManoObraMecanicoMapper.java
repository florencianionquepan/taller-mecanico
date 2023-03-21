package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ManoObraMecanicoDTO;
import com.besysoft.taller.model.ManoObra;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IManoObraMecanicoMapper {
    ManoObraMecanicoDTO mapToDto(ManoObra entidad);
    ManoObra mapToEntity(ManoObraMecanicoDTO dto);
}
