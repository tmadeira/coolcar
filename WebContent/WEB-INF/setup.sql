DROP TABLE IF EXISTS Filial CASCADE;
CREATE TABLE Filial (
  id_filial                       SERIAL,
  id_funcionario_cadastrou        INT DEFAULT NULL,
  nome                            VARCHAR(255) NOT NULL,
  endereco_logradouro             VARCHAR(255),
  endereco_numero                 INT,
  endereco_complemento            VARCHAR(255),
  endereco_cep                    CHAR(8),
  endereco_cidade                 VARCHAR(255),
  endereco_estado                 CHAR(2),
  coordenadas_longitude           DECIMAL(10,8),
  coordenadas_latitude            DECIMAL(10,8),

  CONSTRAINT FILIAL_PK PRIMARY KEY(id_filial)
);

DROP TABLE IF EXISTS Usuario CASCADE;
CREATE TABLE Usuario (
  id_usuario                        SERIAL PRIMARY KEY,
  nome                              VARCHAR(255) NOT NULL,
  email                             VARCHAR(255) NOT NULL UNIQUE,
  senha                             CHAR(10) NOT NULL,
  endereco_logradouro               VARCHAR(255),
  endereco_numero                   INT,
  endereco_complemento              VARCHAR(255),
  endereco_cep                      CHAR(8),
  endereco_cidade                   VARCHAR(255),
  endereco_estado                   CHAR(2)
);

DROP TABLE IF EXISTS Funcionario CASCADE;
CREATE TABLE Funcionario (
  id_usuario                        INT PRIMARY KEY,
  cpf                               CHAR(11) NOT NULL UNIQUE,
  dt_ingresso                       DATE NOT NULL,
  dt_nascimento                     DATE NOT NULL,
  filial_trabalha                   INT,

  FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
      ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(filial_trabalha) REFERENCES Filial(id_filial)
      ON UPDATE CASCADE ON DELETE RESTRICT,

  CONSTRAINT maioridade_funcionario
        CHECK (dt_nascimento <= CURRENT_TIMESTAMP - INTERVAL '18 YEARS')
);

ALTER TABLE Filial ADD CONSTRAINT FILIAL_FK
  FOREIGN KEY(id_funcionario_cadastrou) REFERENCES Funcionario(id_usuario)
  ON DELETE RESTRICT ON UPDATE CASCADE;

DROP TABLE IF EXISTS Cliente CASCADE;
CREATE TABLE Cliente (
  id_usuario                        INT NOT NULL PRIMARY KEY,
  cadastro_confirmado				BOOLEAN DEFAULT FALSE,

  FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
    ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS Cliente_PF CASCADE;
CREATE TABLE Cliente_PF (
  id_usuario                        INT PRIMARY KEY,
  cpf                               CHAR(11) NOT NULL UNIQUE,
  dt_nascimento                     DATE NOT NULL,
  sexo                              CHAR(1) NOT NULL,

  FOREIGN KEY(id_usuario) REFERENCES Cliente(id_usuario)
    ON UPDATE CASCADE ON DELETE CASCADE,

  CONSTRAINT maioridade_cliente
    CHECK (dt_nascimento <= CURRENT_TIMESTAMP - INTERVAL '18 YEARS')
);

DROP TABLE IF EXISTS Cliente_PJ CASCADE;
CREATE TABLE Cliente_PJ (
  id_usuario                        INT PRIMARY KEY,
  cnpj                              CHAR(14) NOT NULL UNIQUE,

  FOREIGN KEY(id_usuario) REFERENCES Cliente(id_usuario)
    ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS Modelo CASCADE;
CREATE TABLE Modelo (
    id_modelo                       SERIAL,
    tipo                            VARCHAR(255) NOT NULL,
    nome                            VARCHAR(255) NOT NULL,
    diaria                          DECIMAL(10,2) NOT NULL,
    fabricante                      VARCHAR(255) NOT NULL,
    combustivel                     VARCHAR(16) NOT NULL DEFAULT 'Gasolina',
    id_funcionario                  INT NOT NULL,

    CONSTRAINT MODELO_PK
        PRIMARY KEY(id_modelo),

    CONSTRAINT ID_FUNCIONARIO_FK
        FOREIGN KEY(id_funcionario) REFERENCES Funcionario(id_usuario)
            ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS AcessoriosAdicionais CASCADE;
CREATE TABLE AcessoriosAdicionais (
  id_acessorios                    INT,
  gps                              BOOLEAN NOT NULL DEFAULT FALSE,
  cadeiras_de_bebe                 INT NOT NULL DEFAULT 0,
  seguro                           BOOLEAN NOT NULL DEFAULT FALSE,
  
  CONSTRAINT ACESSORIOSADICIONAIS_PK
  	PRIMARY KEY(id_acessorios)

);

DROP TABLE IF EXISTS Reserva CASCADE;
CREATE TABLE Reserva (
    id_reserva                      SERIAL,
    id_cliente                      INT NOT NULL,
    id_modelo                       INT NOT NULL,
    valor                           DECIMAL(10,2) NOT NULL DEFAULT 0,
    id_filial_retirada              INT NOT NULL,
    dt_inicio_reserva               DATE NOT NULL,
    id_filial_devolucao             INT NOT NULL,
    dt_fim_reserva                  DATE NOT NULL,
	id_acessorios					INT NOT NULL DEFAULT 0,

    CONSTRAINT RESERVA_PK PRIMARY KEY(id_reserva),

    CONSTRAINT CLIENTE_RESERVA_FK
      FOREIGN KEY(id_cliente) REFERENCES Cliente(id_usuario)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT MODELO_RESERVA_FK
      FOREIGN KEY(id_modelo) REFERENCES Modelo(id_modelo)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FILIAL_RETIRADA_RESERVA_FK
      FOREIGN KEY(id_filial_retirada) REFERENCES Filial(id_filial)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FILIAL_DEVOLUCAO_RESERVA_FK
      FOREIGN KEY(id_filial_devolucao) REFERENCES Filial(id_filial)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ACESSORIO_FK
      FOREIGN KEY(id_acessorios) REFERENCES AcessoriosAdicionais(id_acessorios)
        ON DELETE RESTRICT ON UPDATE CASCADE,

    CONSTRAINT VERIFICA_DATA CHECK (dt_inicio_reserva <= dt_fim_reserva)
);

DROP TABLE IF EXISTS Veiculo CASCADE;
CREATE TABLE Veiculo (
    chassi                          CHAR(17),
    placa                           CHAR(7) NOT NULL,
    kilometragem                    INT NOT NULL DEFAULT 0,
    ano                             INT NOT NULL,
    status                          INT NOT NULL DEFAULT 0,
    id_funcionario                  INT NOT NULL,
    id_modelo                       INT NOT NULL,
    filial_alojada                  INT NOT NULL,

    CONSTRAINT VEICULO_PK PRIMARY KEY(chassi),

    CONSTRAINT ID_FUNCIONARIO_FK
      FOREIGN KEY(id_funcionario) REFERENCES Funcionario(id_usuario)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ID_MODELO_FK
      FOREIGN KEY(id_modelo) REFERENCES Modelo(id_modelo)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FILIAL_ALOJADA_FK
      FOREIGN KEY(filial_alojada) REFERENCES Filial(id_filial)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Revisao CASCADE;
CREATE TABLE Revisao (
  id_revisao                      SERIAL,
  id_funcionario                  INT NOT NULL,
  valor                           DECIMAL(10,2) NOT NULL DEFAULT 0,
  observacao                      TEXT,
  data                            DATE,
  local                           VARCHAR(255),
  chassi_veiculo                  CHAR(17) NOT NULL,

  CONSTRAINT REVISAO_PK PRIMARY KEY(id_revisao),

  CONSTRAINT FUNCIONARIO_REVISAO_FK
    FOREIGN KEY(id_funcionario) REFERENCES Funcionario(id_usuario)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT VEICULO_REVISAO_FK
    FOREIGN KEY(chassi_veiculo) REFERENCES Veiculo(chassi)
      ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS CartaoDeCredito CASCADE;
CREATE TABLE CartaoDeCredito (
  numero_cartao                   CHAR(16),
  id_cliente                      INT NOT NULL,
  dt_validade_mes                 NUMERIC(2) NOT NULL,
  dt_validade_ano                 NUMERIC(4) NOT NULL,
  nome                            VARCHAR(255) NOT NULL,
  usar_como_padrao                BOOLEAN NOT NULL DEFAULT FALSE,

  CONSTRAINT CARTAO_PK
    PRIMARY KEY(numero_cartao),
  CONSTRAINT CLIENTE_CARTAO_FK
    FOREIGN KEY(id_cliente) REFERENCES Cliente(id_usuario)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT DATA_VALIDADE
    CHECK(
      dt_validade_ano > EXTRACT(YEAR FROM CURRENT_TIMESTAMP) OR
      (
        dt_validade_ano = EXTRACT(YEAR FROM CURRENT_TIMESTAMP) AND
        dt_validade_mes >= EXTRACT(MONTH FROM CURRENT_TIMESTAMP)
      )
    )
);

DROP TABLE IF EXISTS Locacao CASCADE;
CREATE TABLE Locacao (
  id_locacao                      SERIAL,
  id_funcionario_entrega          INT NOT NULL,
  id_funcionario_recebe           INT NOT NULL,
  id_reserva                      INT NOT NULL,
  filial_retirada                 INT NOT NULL,
  data_retirada                   DATE  NOT NULL,
  filial_devolucao                INT NOT NULL,
  data_devolucao                  DATE NOT NULL,
  arquivo_copia_cnh               VARCHAR(255) NOT NULL,
  chassi_veiculo                  CHAR(17) NOT NULL,

  CONSTRAINT LOCACAO_PK PRIMARY KEY(id_locacao),

  CONSTRAINT ID_FUNCIONARIO_ENTREGA_FK
    FOREIGN KEY(id_funcionario_entrega) REFERENCES Funcionario(id_usuario)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT ID_FUNCIONARIO_RECEBE_FK
    FOREIGN KEY(id_funcionario_recebe) REFERENCES Funcionario(id_usuario)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT ID_RESERVA_FK
    FOREIGN KEY(id_reserva) REFERENCES Reserva(id_reserva)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT FILIAL_RETIRADA_FK
    FOREIGN KEY(filial_retirada) REFERENCES Filial(id_filial)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT FILIAL_DEVOLUCAO_FK
    FOREIGN KEY(filial_devolucao) REFERENCES Filial(id_filial)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT CHASSI_VEICULO_FK
    FOREIGN KEY(chassi_veiculo) REFERENCES Veiculo(chassi)
      ON DELETE RESTRICT ON UPDATE CASCADE,

  CONSTRAINT VERIFICA_DATA CHECK (data_retirada <= data_devolucao)
);

DROP TABLE IF EXISTS CaracteristicasCarro CASCADE;
CREATE TABLE CaracteristicasCarro (
  id_caracteristicas              INT,
  ar_condicionado                 BOOLEAN NOT NULL DEFAULT FALSE,
  direcao_hidraulica              BOOLEAN NOT NULL DEFAULT FALSE,
  cambio_automatico               BOOLEAN NOT NULL DEFAULT FALSE,

  CONSTRAINT CARACTERISTICASCARRO_PK
  	PRIMARY KEY(id_caracteristicas)

);

DROP TABLE IF EXISTS Carro CASCADE;
CREATE TABLE Carro (
  id_modelo                       INT,
  num_portas                      INT NOT NULL DEFAULT 2,
  num_assentos                    INT NOT NULL DEFAULT 5,
  tamanho_portas_malas            INT NOT NULL,
  id_caracteristicas			        INT NOT NULL DEFAULT 1,

  CONSTRAINT CARRO_PK PRIMARY KEY(id_modelo),

  CONSTRAINT ID_MODELO_FK
    FOREIGN KEY(id_modelo) REFERENCES Modelo(id_modelo)
      ON DELETE RESTRICT ON UPDATE CASCADE,
      
  CONSTRAINT ID_CARACTERISTICAS_FK
    FOREIGN KEY(id_caracteristicas) REFERENCES CaracteristicasCarro(id_caracteristicas)
      ON DELETE SET DEFAULT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Moto CASCADE;
CREATE TABLE Moto (
  id_modelo                       INT,
  cilindradas                     DECIMAL(10,2) NOT NULL,
  tamanho_tanque                  DECIMAL(10,2) NOT NULL,

  CONSTRAINT MOTO_PK PRIMARY KEY(id_modelo),

  CONSTRAINT ID_MODELO_FK
    FOREIGN KEY(id_modelo) REFERENCES Modelo(id_modelo)
      ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Ocorrencia CASCADE;
CREATE TABLE Ocorrencia (
  id_locacao                      INT,
  dt_ocorrencia                   DATE,
  id_funcionario                  INT NOT NULL,
  descricao                       TEXT,

  CONSTRAINT OCORRENCIA_PK PRIMARY KEY(id_locacao, dt_ocorrencia),

  CONSTRAINT ID_LOCACAO_OCORRENCIA_FK
    FOREIGN KEY(id_locacao) REFERENCES Locacao(id_locacao)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT ID_FUNCIONARIO_OCORRENCIA_FK
    FOREIGN KEY(id_funcionario) REFERENCES Funcionario(id_usuario)
      ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Avaliacao CASCADE;
CREATE TABLE Avaliacao (
  id_locacao                  INT,
  id_cliente                  INT,
  nota                        INT NOT NULL,
  comentarios                 TEXT,

  CONSTRAINT AVALIACAO_PK
    PRIMARY KEY(id_locacao, id_cliente),

  CONSTRAINT ID_LOCACAO_AVALIACAO_FK
    FOREIGN KEY(id_locacao) REFERENCES Locacao(id_locacao)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT ID_USUARIO_AVALIACAO_FK
    FOREIGN KEY(id_cliente) REFERENCES Cliente(id_usuario)
      ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS TelefoneFilial CASCADE;
CREATE TABLE TelefoneFilial (
  id_filial                 INT,
  telefone_ddd              CHAR(2),
  telefone_numero           VARCHAR(9),

  CONSTRAINT TELEFONE_FILIAL_PK PRIMARY KEY(id_filial, telefone_ddd, telefone_numero),

  CONSTRAINT TELEFONE_FILIAL_FK
    FOREIGN KEY(id_filial) REFERENCES Filial(id_filial)
      ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS TelefoneUsuario CASCADE;
CREATE TABLE TelefoneUsuario (
  id_usuario                INT,
  telefone_ddd              CHAR(2),
  telefone_numero           VARCHAR(9),

  CONSTRAINT TELEFONE_USUARIO_PK
    PRIMARY KEY(id_usuario, telefone_ddd, telefone_numero),

  CONSTRAINT TELEFONE_USUARIO_FK
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
      ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO Usuario VALUES (DEFAULT, 'Marcos Kazuya Yamazaki', 'mky@coolcar.com', '1251475389', 'Rua Americo Pellini', 274, NULL, '18086329', 'Sorocaba', 'SP');
INSERT INTO Funcionario VALUES (1, '395083659XX', '2015-11-12', '1992-07-28', NULL);

INSERT INTO Filial VALUES (DEFAULT, 1, 'IME', 'Rua do Matão', 1010, NULL, '05508090', 'São Paulo', 'SP', -23.5598948, -46.7337699);
INSERT INTO Filial VALUES (DEFAULT, 1, 'FAU', 'Rua do Lago', 876, NULL, '04280000', 'São Paulo', 'SP', -23.5610024, -46.7294776);
INSERT INTO Filial VALUES (DEFAULT, 1, 'FEA', 'Av. Prof. Luciano Gualberto', 908, NULL, '05508010', 'São Paulo', 'SP', -23.5588353, -46.7313283);
INSERT INTO Filial VALUES (DEFAULT, 1, 'EEFE', 'Av. Professor Mello Moraes', 65, NULL, '05508030', 'São Paulo', 'SP', -23.5625024, -46.71929);
INSERT INTO Filial VALUES (DEFAULT, 1, 'FFLCH', 'Av. Prof. Lineu Prestes', 3380, NULL, '05508000', 'São Paulo', 'SP', -23.560694, -46.7278951);

INSERT INTO Usuario VALUES (DEFAULT, 'Felipe Túlio Pereira da Cruz', 'ftpdc@coolcar.com', '1251475389', 'Rua xx', 1912, NULL, 'xxxxxxxx', 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Tiago Madeira', 'tm@coolcar.com', '1251475389', 'Rua x', 1912, NULL, 'xxxxxxxx', 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Caio Lopes', 'cl@coolcar.com', '1251475389', 'Rua y', 13423, NULL, 'xxxxxxxx', 'Santo André', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Rodrigo Zerbini', 'rz@coolcar.com', '1251475389', 'Rua z', 1912, NULL, 'xxxxxxxx', 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Erika Midori Akabane', 'era@coolcar.com', '1251475389', 'Rua z', 1912, NULL, 'xxxxxxxx', 'São Paulo', 'SP');

INSERT INTO Funcionario VALUES (2, '736285735XX', '2015-11-09', '1990-01-23', 1);
INSERT INTO Funcionario VALUES (3, '236482637XX', '2015-11-12', '1992-07-28', 2);
INSERT INTO Funcionario VALUES (4, '937236482XX', '2015-11-12', '1992-07-28', 3);
INSERT INTO Funcionario VALUES (5, '462849274XX', '2015-11-12', '1992-07-28', 4);
INSERT INTO Funcionario VALUES (6, '111111114XX', '2015-11-13', '1992-08-16', 5);

INSERT INTO Usuario VALUES (DEFAULT, 'Epifânia Santos', 'es@coolcar.com', '1251475389', 'Rua A', 101, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Lurdes Carvalheiro', 'lc@coolcar.com', '1251475389', 'Rua A', 102, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Tobias Sabala', 'ts@coolcar.com', '1251475389', 'Rua B', 101, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Luís Guterres', 'lg@coolcar.com', '1251475389', 'Rua B', 102, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Timóteo Igrejas', 'ti@coolcar.com', '1251475389', 'Rua C', 101, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Dorindo Maia', 'dm@coolcar.com', '1251475389', 'Rua D', 101, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Virgílio Mainha', 'vm@coolcar.com', '1251475389', 'Rua F', 101, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Huangko Wuji', 'hw@coolcar.com', '1251475389', 'Rua R', 101, NULL, NULL, 'São Paulo', 'SP');
INSERT INTO Usuario VALUES (DEFAULT, 'Liubao Zen', 'lz@coolcar.com', '1251475389', 'Rua Z', 101, NULL, NULL, 'São Paulo', 'SP');

INSERT INTO Cliente VALUES (7  , TRUE);
INSERT INTO Cliente VALUES (8  , TRUE);
INSERT INTO Cliente VALUES (9  , TRUE);
INSERT INTO Cliente VALUES (10 , TRUE);
INSERT INTO Cliente VALUES (11 , TRUE);
INSERT INTO Cliente VALUES (12 , TRUE);
INSERT INTO Cliente VALUES (13 , TRUE);
INSERT INTO Cliente VALUES (14 , TRUE);
INSERT INTO Cliente VALUES (15 , TRUE);

INSERT INTO Cliente_PF VALUES (7  , '12463283300', '1990-01-08', 'F');
INSERT INTO Cliente_PF VALUES (8  , '72192601727', '1983-05-18', 'F');
INSERT INTO Cliente_PF VALUES (9  , '32407532007', '1991-02-12', 'M');
INSERT INTO Cliente_PF VALUES (10 , '51407454536', '1983-01-01', 'M');
INSERT INTO Cliente_PF VALUES (11 , '92195173378', '1953-12-25', 'M');
INSERT INTO Cliente_PF VALUES (12 , '53568594900', '1973-09-30', 'M');
INSERT INTO Cliente_PF VALUES (13 , '67416056810', '1964-10-12', 'M');
INSERT INTO Cliente_PF VALUES (14 , '78674144365', '1990-11-04', 'F');
INSERT INTO Cliente_PF VALUES ((select max(id_usuario) from usuario) , '35727556546', '1982-06-23', 'F');

INSERT INTO CaracteristicasCarro VALUES(1, FALSE, FALSE, FALSE);
INSERT INTO CaracteristicasCarro VALUES(2, TRUE , FALSE, FALSE);
INSERT INTO CaracteristicasCarro VALUES(3, FALSE, TRUE , FALSE);
INSERT INTO CaracteristicasCarro VALUES(4, FALSE, FALSE, TRUE );
INSERT INTO CaracteristicasCarro VALUES(5, TRUE , TRUE , FALSE);
INSERT INTO CaracteristicasCarro VALUES(6, TRUE , FALSE, TRUE );
INSERT INTO CaracteristicasCarro VALUES(7, FALSE, TRUE , TRUE );
INSERT INTO CaracteristicasCarro VALUES(8, TRUE , TRUE , TRUE );

INSERT INTO Modelo VALUES (DEFAULT, 'Hatch',  'Uno', 119.90,             'Fiat', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Hatch',  'Palio Fire', 109.90,      'Fiat', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Sedan',  'Siena', 144.90,           'Fiat', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Sedan',  'Linea', 243.90,           'Fiat', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'SUV',    'Freemont', 399.90,        'Fiat', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'SUV',    'Renegade', 287.90,        'Jeep', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Minivan','Idea', 199.90,            'Fiat', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Minivan','Doblò', 249.90,           'Fiat', 'Gasolina/Álcool', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Scooter','Lead', 32.90,            'Honda', 'Gasolina', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Scooter','PCX', 36.90,             'Honda', 'Gasolina', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Street', 'Factor 125 TBR', 40.90, 'Yamaha', 'Gasolina', 1);
INSERT INTO Modelo VALUES (DEFAULT, 'Street', 'Fazer 150 SED', 45.90,  'Yamaha', 'Gasolina', 1);

INSERT INTO Carro VALUES(1, 4, 5, 290, 5);
INSERT INTO Carro VALUES(2, 4, 5, 290, 5);
INSERT INTO Carro VALUES(3, 4, 5, 500, 5);
INSERT INTO Carro VALUES(4, 4, 5, 500, 5);
INSERT INTO Carro VALUES(5, 4, 5, 580, 8);
INSERT INTO Carro VALUES(6, 4, 5, 283, 8);
INSERT INTO Carro VALUES(7, 4, 7, 380, 5);
INSERT INTO Carro VALUES(8, 4, 7, 750, 5);

INSERT INTO Moto VALUES(9,  108, 6.5);
INSERT INTO Moto VALUES(10,150, 8.0);
INSERT INTO Moto VALUES(11,125, 19.5);
INSERT INTO Moto VALUES(12,150, 15.2);

INSERT INTO Veiculo VALUES ('82365723284637471', 'HEN6392', DEFAULT, 2015, 0, 1, 1, 1);
INSERT INTO Veiculo VALUES ('54506848608101697', 'IER6302', DEFAULT, 2015, 0, 1, 1, 1);
INSERT INTO Veiculo VALUES ('10196705251250375', 'BXL2730', DEFAULT, 2015, 0, 1, 2, 1);
INSERT INTO Veiculo VALUES ('62097254808968142', 'HCM2842', DEFAULT, 2015, 0, 1, 2, 1);
INSERT INTO Veiculo VALUES ('65124894564072935', 'CJW1730', DEFAULT, 2015, 0, 1, 3, 2);
INSERT INTO Veiculo VALUES ('95634609419502584', 'MSH1352', DEFAULT, 2015, 0, 1, 3, 3);
INSERT INTO Veiculo VALUES ('21614532201682467', 'NOE1930', DEFAULT, 2015, 0, 1, 4, 4);
INSERT INTO Veiculo VALUES ('46344523523535236', 'WFE5713', DEFAULT, 2015, 0, 1, 4, 4);
INSERT INTO Veiculo VALUES ('32523526342346234', 'TDN7432', DEFAULT, 2015, 0, 1, 5, 2);
INSERT INTO Veiculo VALUES ('98742735618398136', 'WDV7252', DEFAULT, 2015, 0, 1, 5, 3);
INSERT INTO Veiculo VALUES ('15801353808683240', 'JRT6342', DEFAULT, 2015, 0, 1, 6, 5);
INSERT INTO Veiculo VALUES ('81250764023675913', 'QEW4626', DEFAULT, 2015, 0, 1, 6, 5);
INSERT INTO Veiculo VALUES ('95658609419502584', 'RUH2536', DEFAULT, 2015, 0, 1, 7, 3);
INSERT INTO Veiculo VALUES ('21618013201682467', 'RBE2673', DEFAULT, 2015, 0, 1, 7, 2);
INSERT INTO Veiculo VALUES ('13728594893848058', 'HNT6246', DEFAULT, 2015, 0, 1, 8, 1);
INSERT INTO Veiculo VALUES ('16476354028023089', 'VWR6546', DEFAULT, 2015, 0, 1, 9, 4);
INSERT INTO Veiculo VALUES ('74503840926801792', 'WTV7323', DEFAULT, 2015, 0, 1, 10, 1);
INSERT INTO Veiculo VALUES ('15801353124683240', 'WEG6352', DEFAULT, 2015, 0, 1, 11, 5);
INSERT INTO Veiculo VALUES ('12412264023675913', 'THB4616', DEFAULT, 2015, 0, 1, 12, 5);
INSERT INTO Veiculo VALUES ('95658601644302584', 'WEG2516', DEFAULT, 2015, 0, 1, 1, 3);
INSERT INTO Veiculo VALUES ('43664364201682467', 'VWE2653', DEFAULT, 2015, 0, 1, 2, 2);
INSERT INTO Veiculo VALUES ('13728594464361058', 'ACR6216', DEFAULT, 2015, 0, 1, 3, 1);
INSERT INTO Veiculo VALUES ('13643462468023089', 'BYW6516', DEFAULT, 2015, 0, 1, 4, 4);
INSERT INTO Veiculo VALUES ('67464346364801792', 'NBV7353', DEFAULT, 2015, 0, 1, 12, 1);

INSERT INTO AcessoriosAdicionais VALUES (1, TRUE, 0, TRUE);

INSERT INTO Reserva VALUES (DEFAULT, 7, 1, 119.90, 1, '1990-01-08', 1, '1990-01-08', 1);
INSERT INTO Reserva VALUES (DEFAULT, 7, 2, 109.90, 1, '1990-01-08', 1, '1990-01-08', 1);
INSERT INTO Reserva VALUES (DEFAULT, 7, 5, 399.90, 1, '1990-01-11', 1, '1990-01-16', 1);

