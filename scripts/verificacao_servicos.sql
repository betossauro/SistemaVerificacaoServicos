drop
database if exists verificacao_servicos;
create
database verificacao_servicos;
use
verificacao_servicos;

CREATE TABLE TIPOCARGO
(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO     VARCHAR(255)
);

CREATE TABLE TIPOUSUARIO
(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO     VARCHAR(255)
);

CREATE TABLE ATIVIDADE
(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO     VARCHAR(255),
    IDTIPOCARGO   INT,
    FOREIGN KEY (IDTIPOCARGO) REFERENCES TIPOCARGO (ID)
);

CREATE TABLE FUNCIONARIO
(
    ID            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NOME          VARCHAR(255),
    CPF           VARCHAR(11) UNIQUE,
    TELEFONE      VARCHAR(11),
    DATANASCIMENTO  DATE,
    CTPS          VARCHAR(11) UNIQUE,
    MATRICULA 	  VARCHAR(6) UNIQUE,
    SENHA 		  VARCHAR(55),
    DATADESLIGAMENTO DATE,
    IDTIPOCARGO   INT,
    FOREIGN KEY (IDTIPOCARGO) REFERENCES TIPOCARGO (ID),
    IDTIPOUSUARIO INT,
    FOREIGN KEY (IDTIPOUSUARIO) REFERENCES TIPOUSUARIO (ID)
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

CREATE TABLE PRESTACAO
(
    ID            INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    IDFUNCIONARIO INT,
    FOREIGN KEY (IDFUNCIONARIO) REFERENCES FUNCIONARIO (ID),
    IDSALA        INT,
    FOREIGN KEY (IDSALA) REFERENCES SALA (ID),
    DATAINICIO    DATETIME,
    DATAFIM       DATETIME
);

CREATE TABLE PRESTACAO_ATIVIDADE
(
	ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    IDATIVIDADE INT NOT NULL,
    FOREIGN KEY (IDATIVIDADE) REFERENCES ATIVIDADE (ID),
    IDPRESTACAO INT NOT NULL,
    FOREIGN KEY (IDPRESTACAO) REFERENCES PRESTACAO (ID)
);

/* TODO: Classe de ocorrência ficará para a próxima versão do projeto, pois devido ao tempo restante, foi decidido que seria melhor ter a introdução desta classe em um próximo momento.

CREATE TABLE OCORRENCIA
(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO     VARCHAR(255),
    IDPRESTACAO INT NOT NULL,
    FOREIGN KEY (IDPRESTACAO) REFERENCES PRESTACAO (ID)
);
*/

INSERT INTO TIPOUSUARIO (descricao)
VALUES ('ADMINISTRADOR');
INSERT INTO TIPOUSUARIO (descricao)
VALUES ('FUNCIONARIO');

INSERT INTO TIPOCARGO (descricao)
VALUES ('FAXINA');
INSERT INTO TIPOCARGO (descricao)
VALUES ('ZELADORIA');
INSERT INTO TIPOCARGO (descricao)
VALUES ('TECNICO');
INSERT INTO TIPOCARGO (descricao)
VALUES ('GERENCIA');

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

INSERT INTO FUNCIONARIO (nome, cpf, telefone, datanascimento, ctps, matricula, senha, datadesligamento, idtipocargo, idtipousuario) VALUES ('Luis Alberto Weber', '10810699966', '48998577999', '1999-05-19','10810699966', '000001', '000011', null, 4, 1);
INSERT INTO FUNCIONARIO (nome, cpf, telefone, datanascimento, ctps, matricula, senha, datadesligamento, idtipocargo, idtipousuario) VALUES ('Bárbara Luersen', '08721090972', '45999725361', '2000-10-02','08721090972', '000002', '000011', null, 4, 1);
INSERT INTO FUNCIONARIO (nome, cpf, telefone, datanascimento, ctps, matricula, senha, datadesligamento, idtipocargo, idtipousuario) VALUES ('Renato Pietro Igor da Rocha', '31323060456', '42993001327', '1997-06-08','31323060456', '100001', '123456', null, 2, 2);

INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Limpeza rotineira', 1);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Abastecimento', 1);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Limpeza pesada CHAO', 1);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Limpeza pesada JANELAS', 1);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Limpeza pesada PAREDES', 1);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Limpeza pesada FORRO', 1);

INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao ELETRICA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao HIDRAULICA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao AR CONDICIONADO', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao PORTA / JANELA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao LAMPADAS', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao PRAGAS', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao MOBILIA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Manutencao TELEFONIA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Acompanhamento Visita Tecnica ELETRICA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Acompanhamento Visita Tecnica HIDRAULICA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Acompanhamento Visita Tecnica AR CONDICIONADO', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Acompanhamento Visita Tecnica PRAGAS', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Acompanhamento Visita Tecnica MOBILIA', 2);
INSERT INTO ATIVIDADE (descricao, idtipocargo) VALUES ('Acompanhamento Visita Tecnica TELEFONIA', 2);

INSERT INTO OCORRENCIA (descricao) VALUES ('Incidente ELETRICO');
INSERT INTO OCORRENCIA (descricao) VALUES ('Incidente HIDRAULICO');
INSERT INTO OCORRENCIA (descricao) VALUES ('Mau funcionamento do AR CONDICIONADO');
INSERT INTO OCORRENCIA (descricao) VALUES ('Mau funcionamento de PORTA / JANELA');
INSERT INTO OCORRENCIA (descricao) VALUES ('Mau funcionamento LAMPADAS');
INSERT INTO OCORRENCIA (descricao) VALUES ('Mau funcionamento TELEFONIA');
INSERT INTO OCORRENCIA (descricao) VALUES ('Pragas (cupins, formigas e outros)');
INSERT INTO OCORRENCIA (descricao) VALUES ('Mobilia defeituosa');
INSERT INTO OCORRENCIA (descricao) VALUES ('Liquido derramado');
INSERT INTO OCORRENCIA (descricao) VALUES ('Alimento derramado');

SELECT * FROM FUNCIONARIO;
SELECT * FROM SALA;