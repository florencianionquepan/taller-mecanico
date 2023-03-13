package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ClienteDTO;
import com.besysoft.taller.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper implements IClienteMapper{

    private final IPersonaMapper persoMap;

    public ClienteMapper(IPersonaMapper persoMap) {
        this.persoMap = persoMap;
    }

    @Override
    public Cliente mapToEntity(ClienteDTO dto) {
        Cliente entidad=new Cliente();
        entidad.setId(dto.getId());
        entidad.setPersona(this.persoMap.mapToEntity(dto.getPersonaDTO()));
        entidad.setTelefonoLinea(dto.getTel());
        entidad.setCorreoElectronico(dto.getEmail());
        dto.getVehiculos();
        return entidad;
    }

    @Override
    public ClienteDTO mapToDto(Cliente entidad) {
        ClienteDTO dto=new ClienteDTO();
        dto.setId(entidad.getId());
        dto.setPersonaDTO(this.persoMap.mapToDto(entidad.getPersona()));
        dto.setTel(entidad.getTelefonoLinea());
        dto.setEmail(entidad.getCorreoElectronico());
        //dto.setVehiculos(...)
        return dto;
    }

    @Override
    public List<ClienteDTO> mapListToDto(List<Cliente> entidades) {
        return entidades.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<Cliente> mapListToEntities(List<ClienteDTO> dtos) {
        return dtos.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }
}
