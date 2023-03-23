package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.MecanicoDTO;
import com.besysoft.taller.model.Mecanico;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Character.toUpperCase;

@Component
public class MecanicoMapper implements IMecanicoMapper{

    private final IPersonaMapper persoMap;
    private final IManoObraOrdenMapper obraMap;

    public MecanicoMapper(IPersonaMapper persoMap, IManoObraOrdenMapper obraMap) {
        this.persoMap = persoMap;
        this.obraMap = obraMap;
    }

    @Override
    public MecanicoDTO mapToDto(Mecanico entidad) {
        MecanicoDTO dto=new MecanicoDTO();
        dto.setId(entidad.getId());
        dto.setPersonaDTO(this.persoMap.mapToDto(entidad.getPersona()));
        dto.setActivo(entidad.getActivo().toString());
        dto.setEspecialidad(entidad.getEspecialidad());
        dto.setListaManoObra(this.obraMap.mapListToDto(entidad.getListaManoObra()));
        return dto;
    }

    @Override
    public Mecanico mapToEntity(MecanicoDTO dto) {
        Mecanico ent=new Mecanico();
        ent.setId(dto.getId());
        ent.setPersona(this.persoMap.mapToEntity(dto.getPersonaDTO()));
        ent.setActivo(toUpperCase(dto.getActivo().charAt(0)));
        ent.setEspecialidad(dto.getEspecialidad());
        //Nunca voy a ingresar lista de mano de obra dentro de mecanicos
        //No es necesario mapear a entidad la lista de obrasDTO aca
        return ent;
    }

    @Override
    public List<MecanicoDTO> mapListToDto(List<Mecanico> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
