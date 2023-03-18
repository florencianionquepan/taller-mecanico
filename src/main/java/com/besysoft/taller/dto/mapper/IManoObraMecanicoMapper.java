package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ManoObraMecanicoDTO;
import com.besysoft.taller.model.ManoObra;

import java.util.List;

public interface IManoObraMecanicoMapper {
    ManoObra mapToEntity(ManoObraMecanicoDTO dto);
    ManoObraMecanicoDTO mapToDto(ManoObra entidad);
    List<ManoObra> mapListToEntities(List<ManoObraMecanicoDTO> dtos);
    List<ManoObraMecanicoDTO> mapListToDto(List<ManoObra> entities);
}
