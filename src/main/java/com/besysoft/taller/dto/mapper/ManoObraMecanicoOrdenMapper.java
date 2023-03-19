package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ManoObraMecanicoOrdenDTO;
import com.besysoft.taller.model.ManoObra;
import org.springframework.stereotype.Component;

@Component
public class ManoObraMecanicoOrdenMapper implements IManoObraMecanicoOrdenMapper {

    private final IMecanicoMapper mecaMap;
    private final IOrdenTrabajoMapper ordenMap;

    public ManoObraMecanicoOrdenMapper(IMecanicoMapper mecaMap, IOrdenTrabajoMapper ordenMap) {
        this.mecaMap = mecaMap;
        this.ordenMap = ordenMap;
    }

    @Override
    public ManoObra mapToEntity(ManoObraMecanicoOrdenDTO dto) {
        ManoObra enti=new ManoObra();
        enti.setId(dto.getId());

        enti.setMecanico(this.mecaMap.mapToEntity(dto.getMecanico()));
        enti.setOrdenTrabajo(this.ordenMap.mapToEntity(dto.getOrdenTrabajo()));
        return enti;
    }

    @Override
    public ManoObraMecanicoOrdenDTO mapToDto(ManoObra entidad) {
        ManoObraMecanicoOrdenDTO dto=new ManoObraMecanicoOrdenDTO();
        dto.setId(entidad.getId());

        dto.setMecanico(this.mecaMap.mapToDto(entidad.getMecanico()));
        dto.setOrdenTrabajo(this.ordenMap.mapToDto(entidad.getOrdenTrabajo()));
        return dto;
    }
}
