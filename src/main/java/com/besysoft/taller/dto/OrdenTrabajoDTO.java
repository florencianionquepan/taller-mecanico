package com.besysoft.taller.dto;

import com.besysoft.taller.model.EstadoOrden;
import com.besysoft.taller.model.Recepcionista;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

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

    //esto cuando lo creo lo necesito
    @NotNull
    @Valid
    private RecepcionistaDTO recepcionista;

    //se solicitara cuando se haga la factura
    private AdministrativoDTO administrativo;

    ///voy a tener un get buscarPorPatente en controller de vehiculo en el lado del front
    //si o si debe ser de un vehiculo
    @NotNull
    private VehiculoDTO vehiculo;
}
