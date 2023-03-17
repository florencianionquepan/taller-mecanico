package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//DTO de mano de obra sin mecanico asignado.Su mapper sera inyectado por MecanicoMapper
public class ManoObraRespDTO {
    private Long id;
    private String detalle;
    private Time duracionHs;

    @JsonIgnoreProperties(value={"recepcionista","administrativo"})
    private OrdenTrabajoDTO ordenTrabajo;
}
