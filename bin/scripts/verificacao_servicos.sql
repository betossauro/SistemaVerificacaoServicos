drop
database if exists verificacao_servicos;
create
database verificacao_servicos;
use
verificacao_servicos;

CREATE TABLE TIPOSERVICO
(
    IDTIPOSERVICO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO     VARCHAR(255)
);

CREATE TABLE TIPOUSUARIO
(
    IDTIPOUSUARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO     VARCHAR(255)
);

CREATE TABLE FUNCIONARIO
(
    ID            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NOME          VARCHAR(255),
    CPF           VARCHAR(11) UNIQUE,
    TELEFONE      VARCHAR(11),
    DTNASCIMENTO  DATE,
    CTPS          VARCHAR(11) UNIQUE,
    IDTIPOSERVICO INT,
    FOREIGN KEY (IDTIPOSERVICO) REFERENCES TIPOSERVICO (IDTIPOSERVICO),
    IDTIPOUSUARIO INT,
    FOREIGN KEY (IDTIPOUSUARIO) REFERENCES TIPOUSUARIO (IDTIPOUSUARIO)
);

CREATE TABLE ENDERECO
(
    ID     INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    RUA    VARCHAR(55),
    NUMERO VARCHAR(55),
    BAIRRO VARCHAR(55),
    CEP    VARCHAR(8),
    CIDADE VARCHAR(55),
    ESTADO VARCHAR(2)
);

CREATE TABLE SALA
(
    ID         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NUMERO     VARCHAR(10),
    DISPONIVEL BOOLEAN
);

CREATE TABLE PRESTACAO_SERVICO
(
    ID            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    IDFUNCIONARIO INT,
    FOREIGN KEY (IDFUNCIONARIO) REFERENCES FUNCIONARIO (ID),
    IDSALA        INT,
    FOREIGN KEY (IDSALA) REFERENCES SALA (ID),
    DATAINICIO    DATETIME,
    DATAFIM       DATETIME,
    DESCRICAO     VARCHAR(255)
);

INSERT INTO TIPOUSUARIO (descricao)
VALUES ('ADMINISTRADOR');
INSERT INTO TIPOUSUARIO (descricao)
VALUES ('FUNCIONARIO');

INSERT INTO TIPOSERVICO (descricao)
VALUES ('FAXINA');
INSERT INTO TIPOSERVICO (descricao)
VALUES ('ZELADORIA');
INSERT INTO TIPOSERVICO (descricao)
VALUES ('TECNICO');

INSERT INTO SALA (numero, disponivel)
VALUES (101, TRUE);
INSERT INTO SALA (numero, disponivel)
VALUES (102, TRUE);
INSERT INTO SALA (numero, disponivel)
VALUES (103, TRUE);
INSERT INTO SALA (numero, disponivel)
VALUES (201, TRUE);
INSERT INTO SALA (numero, disponivel)
VALUES (202, TRUE);