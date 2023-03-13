package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.VehiculoDTO;
import com.besysoft.taller.model.Vehiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVehiculoMapper {
    @Mapping(source="clientes", target = "listaClientes")
    Vehiculo mapToEntity(VehiculoDTO dto);
    @Mapping(source="listaClientes", target = "clientes")
    VehiculoDTO mapToDto(Vehiculo entidad);
    List<Vehiculo> mapListToEntities(List<VehiculoDTO> dtos);
    List<VehiculoDTO> mapListToDtos(List<Vehiculo> entidades);
}
