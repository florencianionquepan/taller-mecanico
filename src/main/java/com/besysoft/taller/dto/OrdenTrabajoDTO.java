package com.besysoft.taller.dto;

import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.model.Recepcionista;
import com.besysoft.taller.model.Vehiculo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdenTrabajoDTO {

    private Long id;
    private String nivelCombustible;
    private Long kilometraje;
    private String falla;

    //estaran nulos al momento de crearse
    private String formaPago;
    private Integer cantidadCuotas;
    private String tipoTarjeta;

    //esto cuando lo creo lo necesito
    private Recepcionista recepcionista;

    //se solicitara cuando se haga la factura
    private AdministrativoDTO administrativo;

    ///voy a tener un get buscarPorPatente en controller de vehiculo
    private VehiculoDTO vehiculo;
}
