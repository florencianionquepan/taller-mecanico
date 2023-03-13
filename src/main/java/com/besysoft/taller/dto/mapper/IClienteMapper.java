package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ClienteDTO;
import com.besysoft.taller.model.Cliente;

import java.util.List;

public interface IClienteMapper {
    Cliente mapToEntity(ClienteDTO dto);
    ClienteDTO mapToDto(Cliente entidad);
    List<ClienteDTO> mapListToDto(List<Cliente> entidades);
    List<Cliente> mapListToEntities(List<ClienteDTO> dtos);

}
