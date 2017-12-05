CREATE SEQUENCE public.sabores_id_seq
    INCREMENT 1
    START 16
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.sabores_id_seq
    OWNER TO postgres;



-- Table: public.sabores

-- DROP TABLE public.sabores;

CREATE TABLE public.sabores
(
    id integer NOT NULL DEFAULT nextval('sabores_id_seq'::regclass),
    nombre text COLLATE pg_catalog."default" NOT NULL,
    tipo text COLLATE pg_catalog."default" NOT NULL,
    descripcion text COLLATE pg_catalog."default",
    stock integer NOT NULL,
    CONSTRAINT sabores_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.sabores
    OWNER to postgres;


INSERT INTO sabores(nombre,tipo,descripcion,stock) VALUES('Crema Americana','crema','Muy rica','32');
INSERT INTO sabores(nombre,tipo,descripcion,stock) VALUES('Vainilla','crema','Muy rica','30');
INSERT INTO sabores(nombre,tipo,descripcion,stock) VALUES('Frutilla','agua','Riquisima','12');

-- DELETE FROM sabores WHERE id = 2


-- SELECT nombre, tipo FROM sabores 


-- UPDATE sabores SET stock=50 WHERE id=7