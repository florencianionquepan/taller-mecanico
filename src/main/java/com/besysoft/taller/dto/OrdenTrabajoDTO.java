package com.besysoft.taller.dto;

import com.besysoft.taller.model.DetalleOrdenTrabajo;
import com.besysoft.taller.model.EstadoOrden;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenTrabajoDTO {
    @NotNull
    private Long id;
    private EstadoOrden estado;
    private String nivelCombustible;
    private Long kilometraje;
    private String falla;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    //estaran nulos al momento de crearse
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    private String formaPago;
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    private BigDecimal importeTotal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinReparacion;

    private RecepcionistaDTO recepcionista;

    //se solicitara en facturada
    private AdministrativoDTO administrativo;

    private VehiculoDTO vehiculo;

}
