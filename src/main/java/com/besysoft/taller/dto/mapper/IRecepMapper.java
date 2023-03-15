package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.RecepcionistaDTO;
import com.besysoft.taller.model.Recepcionista;

import java.util.List;

public interface IRecepMapper {
    Recepcionista mapToEntity(RecepcionistaDTO dto);
    RecepcionistaDTO mapToDto(Recepcionista ent);
    List<RecepcionistaDTO> mapListToDto(List<Recepcionista> entidades);
}
