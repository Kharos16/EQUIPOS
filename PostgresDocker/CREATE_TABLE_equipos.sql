CREATE TABLE equipos
(
    id SERIAL PRIMARY KEY,
    nombre      varchar(30) NOT NULL,
    puntos      SMALLINT NOT NULL,
    ciudad      varchar(30) NOT NULL,
    nombre_dt       varchar(40) NULL
);