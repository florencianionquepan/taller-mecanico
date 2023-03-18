package com.besysoft.taller.dto.mapper;

import com.besysoft.taller.dto.ManoObraMecanicoDTO;
import com.besysoft.taller.model.ManoObra;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManoObraMecanicoMapper implements IManoObraMecanicoMapper{

    private final IMecanicoMapper mecaMap;

    public ManoObraMecanicoMapper(IMecanicoMapper mecaMap) {
        this.mecaMap = mecaMap;
    }

    @Override
    public ManoObra mapToEntity(ManoObraMecanicoDTO dto) {
        ManoObra enti=new ManoObra();
        enti.setId(dto.getId());
        enti.setDetalle(dto.getDetalle());
        enti.setDuracionHs(dto.getDuracionHs());
        enti.setMecanico(this.mecaMap.mapToEntity(dto.getMecanico()));
        return enti;
    }

    @Override
    public ManoObraMecanicoDTO mapToDto(ManoObra entidad) {
        ManoObraMecanicoDTO dto=new ManoObraMecanicoDTO();
        dto.setId(entidad.getId());
        dto.setDetalle(entidad.getDetalle());
        dto.setDuracionHs(entidad.getDuracionHs());
        dto.setMecanico(this.mecaMap.mapToDto(entidad.getMecanico()));
        return dto;
    }

    @Override
    public List<ManoObra> mapListToEntities(List<ManoObraMecanicoDTO> dtos) {
        return dtos.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public List<ManoObraMecanicoDTO> mapListToDto(List<ManoObra> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }
}
