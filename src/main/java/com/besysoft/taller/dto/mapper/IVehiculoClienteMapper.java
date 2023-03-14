package com.besysoft.taller.dto.mapper;


import com.besysoft.taller.dto.VehiculoDTO;
import com.besysoft.taller.model.Vehiculo;

public interface IVehiculoClienteMapper {
    VehiculoDTO mapToDto(Vehiculo vehiculo);
}
