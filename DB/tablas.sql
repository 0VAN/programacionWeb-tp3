


/* 	Sequencias para los ID, este numero comienza a partir de 1000
	de manera a evitar conflictos con los datos semillas
*/ 

create sequence seq_proveedor
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_cliente
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_factura
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_producto
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_venta
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_compra
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_compra_detalle
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_venta_detalle
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

create sequence seq_solicitud_compra
  start with 1000
  increment by 1
  maxvalue 99999
  minvalue 1;

/* Tablas */
CREATE TABLE proveedor (
	id bigint PRIMARY KEY DEFAULT nextval('seq_proveedor'),
	descripcion VARCHAR(100)
);

CREATE TABLE cliente (
	id bigint PRIMARY KEY DEFAULT nextval('seq_cliente'),
	nombre VARCHAR(100),
	cedula_identidad VARCHAR(50) NOT NULL DEFAULT nextval('seq_cliente'::regclass),
	CONSTRAINT unique_cedula UNIQUE (cedula_identidad)
);

CREATE TABLE factura (
	id bigint PRIMARY KEY DEFAULT nextval('seq_factura'),
	monto VARCHAR(50),
	fecha timestamp without time zone NOT NULL DEFAULT now()
);

CREATE TABLE producto (
	id bigint PRIMARY KEY DEFAULT nextval('seq_producto'),
	descripcion VARCHAR(100) NOT NULL,
	stock bigint,
	precio bigint
);

CREATE TABLE venta (
  	id bigint DEFAULT nextval('seq_venta'),
  	cliente_id bigint,
	fecha timestamp without time zone NOT NULL DEFAULT now(),
	factura_id  bigint,
	monto bigint, 
	CONSTRAINT venta_pkey PRIMARY KEY (id),
	CONSTRAINT cliente_fkey FOREIGN KEY (cliente_id)
	  REFERENCES cliente (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT factura_fkey FOREIGN KEY (factura_id)
	  REFERENCES factura (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE venta_detalle (
	id bigint DEFAULT nextval('seq_venta_detalle'),
	venta_id bigint,
	producto_id bigint,
	cantidad bigint CHECK (cantidad > 0),
	CONSTRAINT venta_detalle_pkey PRIMARY KEY (id),
	CONSTRAINT venta_fkey FOREIGN KEY (venta_id)
	  REFERENCES venta (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT producto_fkey FOREIGN KEY (producto_id)
	  REFERENCES producto (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE compra (
  	id bigint DEFAULT nextval('seq_compra'),
  	proveedor_id bigint,
	fecha timestamp without time zone NOT NULL DEFAULT now(),
	monto bigint, 
	CONSTRAINT compra_pkey PRIMARY KEY (id),
	CONSTRAINT proveedor_fkey FOREIGN KEY (proveedor_id)
	  REFERENCES proveedor (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE compra_detalle (
	id bigint DEFAULT nextval('seq_compra_detalle'),
	compra_id bigint,
	producto_id bigint,
	cantidad bigint CHECK (cantidad > 0),
	CONSTRAINT compra_detalle_pkey PRIMARY KEY (id),
	CONSTRAINT compra_fkey FOREIGN KEY (compra_id)
	  REFERENCES compra (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT producto_fkey FOREIGN KEY (producto_id)
	  REFERENCES producto (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE solicitud_compra (
	id bigint DEFAULT nextval('seq_compra_detalle'),
	fecha timestamp without time zone NOT NULL DEFAULT now(),
	producto_id bigint,
	atendido boolean DEFAULT false,
	CONSTRAINT solicitud_compra_pkey PRIMARY KEY (id),
	CONSTRAINT producto_fkey FOREIGN KEY (producto_id)
	  REFERENCES producto (id) MATCH SIMPLE
	  ON UPDATE CASCADE ON DELETE RESTRICT
);