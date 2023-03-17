package com.besysoft.taller.dto.mapper;


import com.besysoft.taller.dto.ManoObraMecanicoDTO;
import com.besysoft.taller.model.ManoObra;

public interface IManoObraMecanicoMapper {
    ManoObra mapToEntity(ManoObraMecanicoDTO dto);
    ManoObraMecanicoDTO mapToDto(ManoObra entidad);
}
