package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.MecanicoDTO;
import com.besysoft.taller.model.Mecanico;

import java.util.List;

public interface IMecanicoMapper {
    MecanicoDTO mapToDto(Mecanico entidad);
    Mecanico mapToEntity(MecanicoDTO dto);
    List<MecanicoDTO> mapListToDto(List<Mecanico> entidades);
}
