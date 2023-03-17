package com.besysoft.taller.dto;

import com.besysoft.taller.model.DetalleOrdenTrabajo;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RepuestoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String repuesto;

    @NotNull
    @Digits(integer = 17,fraction = 2)
    private BigDecimal valor;

    private List<DetalleOrdenTrabajo> listadetalleOrdenes;
}
