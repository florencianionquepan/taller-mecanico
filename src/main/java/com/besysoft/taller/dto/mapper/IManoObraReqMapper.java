package com.besysoft.taller.dto.mapper;


import com.besysoft.taller.dto.ManoObraReqDTO;
import com.besysoft.taller.model.ManoObra;

public interface IManoObraReqMapper {
    ManoObra mapToEntity(ManoObraReqDTO dto);
    ManoObraReqDTO mapToDto(ManoObra entidad);
}
