# taller-mecanico
Trabajo integrador del bootcamp dictado por Besysoft

##Caso Practico
file:///home/florencia/Im%C3%A1genes/Capturas%20de%20pantalla/Captura%20desde%202023-03-23%2020-03-22.png![imagen](https://user-images.githubusercontent.com/85314154/232899980-08bfb808-8462-41e6-8207-55e3a975902e.png)

###Actividades del caso
1. Recibe cliente y vehículo:
• Valida existencia de Vehículo por patente.
• Si existe Vehículo se validan los datos del Cliente.
• Si no existe Vehículo se valida Cliente por e-mail.
• Si existe Cliente, se vincula el Vehículo al cliente.
• Si no existe Cliente ni Vehículo, se registran a ambos.
2. Genera Orden de Trabajo:
• Registra nivel de combustible, kilometraje y detalle de la falla o visita al
taller. Se asigna fecha del día a la Orden de Trabajo. La misma queda
en estado Creada.
• Asigna Mecánico.
3. Genera Mano de Obra:
• El mecánico asignado crea la Mano de Obra asociada a la Orden de
trabajo.
4. Inicia Reparación:
• Cambia el estado de la orden de trabajo de Creada a En reparación.
• Al finalizar la reparación, completa detalles y tiempo de la misma en la
Mano de obra.
• Se cargan los repuestos a la Orden de trabajo.
• Se cambia el estado de la Orden de trabaja de En reparación a Para
facturar.
5. Factura y cobra:
• Se toma la Orden de trabajo en estado Para facturar y del detalle de la
misma se genera la factura, obteniendo el importe total.
• Se pasa del estado Para facturar a Facturada.
• Se registra el Empleado Administrativo y el pago dependiendo la
Forma de Pago, Tipo de Tarjeta y Cantidad de cuotas en caso de
aplicar.
6. Entrega de Vehículo:
• Se cierra la Orden de Trabajó pasando del estado Facturada a Cerrada

###DER
file:///home/florencia/Im%C3%A1genes/Capturas%20de%20pantalla/Captura%20desde%202023-03-23%2020-06-13.png![imagen](https://user-images.githubusercontent.com/85314154/232900495-c0383b1e-6c87-4f24-9a95-fef88a7d9176.png)
