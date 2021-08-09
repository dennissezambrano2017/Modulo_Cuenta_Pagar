/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  elect
 * Created: 09-ago-2021
 */

-- En este archivo solo son consultas de ejemplos

-- facturas.
select *
    from facturas;

-- insertar factura
insert into factura(nfactura, descripcion, importe, pagado, fecha, vencimiento, estado) 
    values ('000-003', 'Compra de foco', 100, 24, '09/08/2021', '11/08/2021', 0);

-- mostrar infomracion de la tabla de tipo comprobante.
\d "tipoComprobante"

-- insertar un tipo de comprobante (retencion)


