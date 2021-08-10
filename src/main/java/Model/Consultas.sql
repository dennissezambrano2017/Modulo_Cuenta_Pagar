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
insert into "tipoComprobante" ("idTipoComprobante", descripcion) 
    values (1, 'Retención');

-- seleciona todo
select *
    from "tipoComprobante";

-- traer una factura condicionado por el id
select idfactura, nfactura, descripcion, importe, pagado, fecha, vencimiento, estado, idproveedor, idasiento
    from factura
    where "idfactura"=22;

-- actualización de la retenciona para tener la factura 22
update retencion set "idFacturaCompra"=30 where "idRetencion"=2;


-- selecion del proveedor por id
select idproveedor, codigo, razonsocial, ruc, nombre, direccion, email, webpage, contacto, telefono, estado
    from proveedor
    where idproveedor=1;

-- ACTUALIZA FACTURA -> PROVEEDOR.
update factura set idproveedor=1
    where idfactura=30;


-- selecionar todos los registros de anticipo
select "idAnticipo", importe, "fechaRegistro", descripcion, "idProveedor"
    from anticipo;

-- insertar un anticipo
insert into anticipo(importe, "fechaRegistro", descripcion, "idProveedor")
    values (222, '10/08/2021', 'Reparación', 1);

-- actualizar anticipo
update anticipo set "importe"=33, "fechaRegistro"='10/05/2021', descripcion='sdffsdf', "idProveedor"=1
    where "idAnticipo"=17;