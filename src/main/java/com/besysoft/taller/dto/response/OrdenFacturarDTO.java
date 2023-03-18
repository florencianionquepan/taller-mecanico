package com.besysoft.taller.dto.response;

import com.besysoft.taller.dto.DetalleOrdenDTO;
import com.besysoft.taller.dto.ManoObraDTO;
import com.besysoft.taller.dto.VehiculoDTO;
import com.besysoft.taller.model.EstadoOrden;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenFacturarDTO {
    @NotNull
    private Long id;
    private EstadoOrden estado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinReparacion;
    private VehiculoDTO vehiculo;
    @NotNull
    @Valid
    private List<ManoObraDTO> listaManoObra;
    @NotNull
    @Valid
    private List<DetalleOrdenDTO> detalleOrdenes;
}
