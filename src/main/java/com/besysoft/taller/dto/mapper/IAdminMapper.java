package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.AdministrativoDTO;
import com.besysoft.taller.model.Administrativo;

import java.util.List;

public interface IAdminMapper {
    Administrativo mapToEntity(AdministrativoDTO dto);
    AdministrativoDTO mapToDto(Administrativo entidad);
    List<AdministrativoDTO> mapToListDto(List<Administrativo> entidades);
}
