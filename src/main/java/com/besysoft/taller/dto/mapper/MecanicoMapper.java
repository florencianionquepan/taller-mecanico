package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.MecanicoDTO;
import com.besysoft.taller.model.EstadoOrden;
import com.besysoft.taller.model.ManoObra;
import com.besysoft.taller.model.Mecanico;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MecanicoMapper implements IMecanicoMapper{

    private final IPersonaMapper persoMap;

    public MecanicoMapper(IPersonaMapper persoMap) {
        this.persoMap = persoMap;
    }

    @Override
    public MecanicoDTO mapToDto(Mecanico entidad) {
        MecanicoDTO dto=new MecanicoDTO();
        dto.setId(entidad.getId());
        dto.setPersonaDTO(this.persoMap.mapToDto(entidad.getPersona()));
        dto.setActivo(entidad.getActivo());
        dto.setEspecialidad(entidad.getEspecialidad());
        dto.setListaManoObra(entidad.getListaManoObra());
        return dto;
    }

    @Override
    public Mecanico mapToEntity(MecanicoDTO dto) {
        Mecanico ent=new Mecanico();
        ent.setId(dto.getId());
        ent.setPersona(this.persoMap.mapToEntity(dto.getPersonaDTO()));
        ent.setActivo(dto.getActivo());
        ent.setEspecialidad(dto.getEspecialidad());
        dto.getListaManoObra();
        return ent;
    }

    @Override
    public List<MecanicoDTO> mapListToDto(List<Mecanico> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
