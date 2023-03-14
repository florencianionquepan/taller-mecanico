package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.AdministrativoDTO;
import com.besysoft.taller.model.Administrativo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminMapper implements IAdminMapper{

    private final IPersonaMapper persoMap;

    public AdminMapper(IPersonaMapper persoMap) {
        this.persoMap = persoMap;
    }

    @Override
    public Administrativo mapToEntity(AdministrativoDTO dto) {
        Administrativo admin=new Administrativo();
        admin.setId(dto.getId());
        admin.setPersona(this.persoMap.mapToEntity(dto.getPersonaDTO()));
        return admin;
    }

    @Override
    public AdministrativoDTO mapToDto(Administrativo entidad) {
        AdministrativoDTO dto=new AdministrativoDTO();
        dto.setId(entidad.getId());
        dto.setPersonaDTO(this.persoMap.mapToDto(entidad.getPersona()));
        return dto;
    }

    @Override
    public List<AdministrativoDTO> mapToListDto(List<Administrativo> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
