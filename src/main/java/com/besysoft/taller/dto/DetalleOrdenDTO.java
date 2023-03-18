package com.besysoft.taller.dto;

import com.besysoft.taller.model.OrdenTrabajo;
import com.besysoft.taller.model.Repuesto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleOrdenDTO {
    private Long id;
    @NotNull
    @Size(min=1)
    private Integer cantidad;
    @Digits(integer = 17, fraction = 2)
    private BigDecimal valorTotal;
    @NotNull
    @Valid
    private RepuestoDTO repuesto;
}
