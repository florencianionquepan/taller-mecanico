package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.VehiculoDTO;
import com.besysoft.taller.model.Vehiculo;
import org.springframework.stereotype.Component;

@Component
//este mapeo se crea a fin de evitar dependencia circular
//cuando quiero el dto de vehiculo con sus clientes
public class VehiculoClienteMapper implements IVehiculoClienteMapper{

    private final IVehiculoMapper vehiMap;
    private final IClienteMapper clienteMap;

    public VehiculoClienteMapper(IVehiculoMapper vehiMap, IClienteMapper clienteMap) {
        this.vehiMap = vehiMap;
        this.clienteMap = clienteMap;
    }

    @Override
    public VehiculoDTO mapToDto(Vehiculo ent) {
        VehiculoDTO dto=this.vehiMap.mapToDto(ent);
        dto.setClientes(this.clienteMap.mapListToDto(ent.getListaClientes()));
        return dto;
    }
}
