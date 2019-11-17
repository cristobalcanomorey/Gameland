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