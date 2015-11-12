DROP TABLE IF EXISTS Filial CASCADE;
CREATE TABLE Filial (
  id_filial                       SERIAL,
  id_funcionario_cadastrou        INT NOT NULL,
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
  senha                             CHAR(32) NOT NULL,
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
  filial_trabalha                   INT NOT NULL,

  FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
      ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(filial_trabalha) REFERENCES Filial(id_filial)
      ON UPDATE CASCADE ON DELETE RESTRICT,

  CONSTRAINT maioridade_funcionario
        CHECK (dt_nascimento >= CURRENT_TIMESTAMP - INTERVAL '18 YEARS')
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
    CHECK (dt_nascimento >= CURRENT_TIMESTAMP - INTERVAL '18 YEARS')
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
    marca                           VARCHAR(255) NOT NULL,
    kilometragem                    INT NOT NULL DEFAULT 0,
    ano                             INT NOT NULL,
    nome                            VARCHAR(255) NOT NULL,
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
  tipo                            VARCHAR(255) NOT NULL,
  num_portas                      INT NOT NULL DEFAULT 2,
  num_assentos                    INT NOT NULL DEFAULT 5,
  tamanho_portas_malas            INT NOT NULL,
  id_caracteristicas			  INT NOT NULL DEFAULT 0,

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
  cilindradas                     INT NOT NULL,
  tamanho_tanque                  INT NOT NULL,

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


INSERT INTO Filial VALUES (DEFAULT, NULL, 'IME', 'Rua do Matão', 1010, NULL, '05508090', 'São Paulo', 'SP', -23.5598948, -46.7337699);
