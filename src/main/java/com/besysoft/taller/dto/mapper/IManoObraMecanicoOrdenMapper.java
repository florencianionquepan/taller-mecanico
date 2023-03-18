package com.besysoft.taller.dto.mapper;


import com.besysoft.taller.dto.ManoObraMecanicoOrdenDTO;
import com.besysoft.taller.model.ManoObra;

public interface IManoObraMecanicoOrdenMapper {
    ManoObra mapToEntity(ManoObraMecanicoOrdenDTO dto);
    ManoObraMecanicoOrdenDTO mapToDto(ManoObra entidad);
}
