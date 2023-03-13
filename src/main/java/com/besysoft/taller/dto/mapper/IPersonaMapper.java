package com.besysoft.taller.dto.mapper;


import com.besysoft.taller.dto.PersonaDTO;
import com.besysoft.taller.model.Persona;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPersonaMapper {

    Persona mapToEntity(PersonaDTO dto);
    PersonaDTO mapToDto(Persona entidad);
    List<PersonaDTO> mapListToDto(List<Persona> personas);
}
