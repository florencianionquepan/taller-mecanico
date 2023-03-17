package com.besysoft.taller.dto;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//DTO de mano de obra sin mecanico asignado.Su mapper sera inyectado por MecanicoDTO
public class ManoObraDTO {
    private Long id;
    private String detalle;
    private Time duracionHs;

    private OrdenTrabajoDTO ordenTrabajo;
}
