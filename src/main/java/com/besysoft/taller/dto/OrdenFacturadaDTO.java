package com.besysoft.taller.dto;

import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.model.EstadoOrden;
import com.besysoft.taller.model.Vehiculo;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenFacturadaDTO {
    private Long id;
    private EstadoOrden estado;

    private LocalDateTime fechaPago;
    @NotNull
    @NotEmpty
    private String formaPago;
    @Min(1)
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    @Digits(integer=17,fraction = 2)
    private BigDecimal importeTotal;

    @NotNull
    @Valid
    private AdministrativoDTO administrativo;
    private VehiculoDTO vehiculo;
}
