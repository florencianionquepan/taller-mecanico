package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ManoObraReqDTO;
import com.besysoft.taller.model.ManoObra;

import java.sql.Time;

public class ManoObraReqMapper implements IManoObraReqMapper{

    private final IMecanicoMapper mecaMap;
    private final IOrdenTrabajoMapper ordenMap;

    public ManoObraReqMapper(IMecanicoMapper mecaMap, IOrdenTrabajoMapper ordenMap) {
        this.mecaMap = mecaMap;
        this.ordenMap = ordenMap;
    }

    @Override
    public ManoObra mapToEntity(ManoObraReqDTO dto) {
        ManoObra enti=new ManoObra();
        enti.setId(dto.getId());
        enti.setDetalle(dto.getDetalle());
        enti.setDuracionHs(dto.getDuracionHs());
        enti.setMecanico(this.mecaMap.mapToEntity(dto.getMecanico()));
        enti.setOrdenTrabajo(this.ordenMap.mapToEntity(dto.getOrdenTrabajo()));
        return enti;
    }

    @Override
    public ManoObraReqDTO mapToDto(ManoObra entidad) {
        ManoObraReqDTO dto=new ManoObraReqDTO();
        dto.setId(entidad.getId());
        dto.setDetalle(entidad.getDetalle());
        dto.setDuracionHs(entidad.getDuracionHs());
        dto.setMecanico(this.mecaMap.mapToDto(entidad.getMecanico()));
        dto.setOrdenTrabajo(this.ordenMap.mapToDto(entidad.getOrdenTrabajo()));
        return dto;
    }
}
