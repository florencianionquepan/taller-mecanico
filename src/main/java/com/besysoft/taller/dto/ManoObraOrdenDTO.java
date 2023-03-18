package com.besysoft.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//DTO de mano de obra sin mecanico asignado.Su mapper sera inyectado por MecanicoMapper
public class ManoObraOrdenDTO {
    private Long id;
    private String detalle;
    @Temporal(TemporalType.TIME)
    private Date duracionHs;

    @JsonIgnoreProperties(value={"recepcionista","administrativo"})
    private OrdenTrabajoDTO ordenTrabajo;
}
