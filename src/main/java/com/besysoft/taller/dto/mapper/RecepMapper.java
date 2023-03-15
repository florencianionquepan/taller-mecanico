package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.RecepcionistaDTO;
import com.besysoft.taller.model.Recepcionista;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecepMapper implements IRecepMapper{

    private final IPersonaMapper persoMapper;

    public RecepMapper(IPersonaMapper persoMapper) {
        this.persoMapper = persoMapper;
    }

    @Override
    public Recepcionista mapToEntity(RecepcionistaDTO dto) {
        Recepcionista ent=new Recepcionista();
        ent.setId(dto.getId());
        ent.setPersona(this.persoMapper.mapToEntity(dto.getPersonaDTO()));
        return ent;
    }

    @Override
    public RecepcionistaDTO mapToDto(Recepcionista ent) {
        RecepcionistaDTO dto=new RecepcionistaDTO();
        dto.setId(ent.getId());
        dto.setPersonaDTO(this.persoMapper.mapToDto(ent.getPersona()));
        return dto;
    }

    @Override
    public List<RecepcionistaDTO> mapListToDto(List<Recepcionista> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
