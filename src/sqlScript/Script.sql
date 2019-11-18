-- Crea la base de datos
CREATE DATABASE gameland;
use gameland;
-- Crea el usuario
CREATE USER IF NOT EXISTS 'tofol'@'localhost' IDENTIFIED BY '1234';

-- Otorgación de permisos a l'usuari
GRANT ALL PRIVILEGES ON gameland.* TO 'tofol'@'localhost' WITH GRANT OPTION;

-- Creación de las tablas
CREATE TABLE IF NOT EXISTS usuario (
	id INT(11) AUTO_INCREMENT PRIMARY KEY,   
	nombre VARCHAR(100) NOT NULL,
	usuario VARCHAR(100) NOT NULL,
	passwd VARCHAR(100) NOT NULL,
	foto VARCHAR(255),
	administrador boolean NOT NULL
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS plataforma (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS genero (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS juego (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
	titulo VARCHAR(255) NOT NULL,
	anyo INT(11) NOT NULL,
	foto VARCHAR(255) NOT NULL,
	descripcion VARCHAR(255) NOT NULL,
	idGenero INT(11) NOT NULL,
	idPlataforma INT(11) NOT NULL,
	foreign key (idGenero)
   	 references genero (id)
    	on delete restrict
    	on update cascade,
    foreign key (idPlataforma)
   	 references plataforma (id)
   	 on delete restrict
    	on update cascade
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS valoracion (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
	idJuego INT(11) NOT NULL,
	idUsuario INT(11) NOT NULL,
	valoracion INT(11) NOT NULL,
	foreign key (idJuego)
   	 references juego (id)
    	on delete cascade
    	on update restrict,
    foreign key (idUsuario)
   	 references usuario (id)
    	on delete cascade
    	on update restrict
) ENGINE=INNODB;

-- Datos de ejemplo
-- Usuarios:
INSERT INTO usuario (nombre,usuario,passwd,foto,administrador)
values ('Tomas', 'patata23','password','patata23.png',false);

INSERT INTO usuario (nombre,usuario,passwd,foto,administrador)
values ('Tofol', 'mstrOfPuppies','1234','mstrOfPuppies.jpg',true);

INSERT INTO usuario (nombre,usuario,passwd,foto,administrador)
values ('Namenos', 'namenos','1234','namenos.png',false);

-- Generos
INSERT INTO genero (nombre)
values ('RPG');

INSERT INTO genero (nombre)
values ('Simulador');

INSERT INTO genero (nombre)
values ('Plataformas 2D');

-- Plataformas
INSERT INTO plataforma (nombre)
values ('Play station 14');

INSERT INTO plataforma (nombre)
values ('Xbox 540');

INSERT INTO plataforma (nombre)
values ('Nintendo M');

INSERT INTO plataforma (nombre)
values ('Gamer PC');

-- Juegos
INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Death Stranding',2019,'Juegos/Death_Stranding.png','Un señor de correos y su bebé en lata intentan llevar paquetes esquivando muertos y malos con poderes',1,2);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super Mario Maker 2',2019,'Juegos/SMM2.png','Un señor fontanero y su hermano intentan agarrarse a banderas esquivando tortugas y mal diseño de niveles',3,3);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super relleno',2018,'Juegos/relleno.png','Un señor relleno y su relleno intentan relleno a relleno esquivando relleno y mal relleno de relleno',1,4);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super relleno2',2018,'Juegos/relleno2.png','Un señor relleno y su relleno intentan relleno a relleno esquivando relleno y mal relleno de relleno',2,4);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super relleno3',2018,'Juegos/relleno3.png','Un señor relleno y su relleno intentan relleno a relleno esquivando relleno y mal relleno de relleno',2,4);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super relleno4',2018,'Juegos/relleno4.png','Un señor relleno y su relleno intentan relleno a relleno esquivando relleno y mal relleno de relleno',2,4);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super relleno remake',2018,'Juegos/rellenoRemake.png','Un señor relleno y su relleno intentan relleno a relleno esquivando relleno y mal relleno de relleno',3,4);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super relleno5',2018,'Juegos/relleno5.png','Un señor relleno y su relleno intentan relleno a relleno esquivando relleno y mal relleno de relleno',3,2);

INSERT INTO juego (titulo,anyo,foto,descripcion,idGenero,idPlataforma)
values ('Super relleno6',2018,'Juegos/relleno6.png','Un señor relleno y su relleno intentan relleno a relleno esquivando relleno y mal relleno de relleno',3,3);


-- Valoraciones
INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('1','1',10);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('1','2',10);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('1','3',9);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('2','1',10);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('2','2',0);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('2','3',6);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('3','1',10);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('3','2',10);

INSERT INTO valoracion (idJuego,idUsuario,valoracion)
values ('3','3',1);