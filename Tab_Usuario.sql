CREATE TABLE usuario(
  id_usuario int(11) NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  login varchar(15) NOT NULL,
  email varchar(100) NOT NULL,
  senha varchar(10) NOT NULL,
  nascimento date NOT NULL,
  telefone varchar(25) NOT NULL,
  idioma varchar(5) NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_usuario),
  UNIQUE KEY login (login)
  );