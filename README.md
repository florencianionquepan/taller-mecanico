# taller-mecanico
Trabajo integrador del bootcamp dictado por Besysoft

## Caso Practico
![Captura desde 2023-03-23 20-03-22(1)](https://user-images.githubusercontent.com/85314154/232901929-053f23f0-3a6a-43a0-8fe5-3ef9018f837a.jpg)

### Actividades del caso
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

### DER
![Captura desde 2023-03-23 20-06-13](https://user-images.githubusercontent.com/85314154/232901059-715fd363-a9d1-45e8-9d85-b5e4b50a4004.png)

