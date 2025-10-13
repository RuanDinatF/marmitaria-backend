DROP DATABASE IF EXISTS marmitaria;
CREATE DATABASE marmitaria;
USE marmitaria;

-- Tabela Clientes
CREATE TABLE Clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    saldo DOUBLE,
    limite_credito boolean
);

-- Tabela TipoProduto
CREATE TABLE TipoProduto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

-- Tabela Produto
CREATE TABLE Produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_produto BIGINT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    quantidadeEstoque DOUBLE,
    estoqueMinimo DOUBLE,
    preco_venda DOUBLE,
    FOREIGN KEY (id_tipo_produto) REFERENCES TipoProduto(id)
);

-- Tabela MovimentacaoFinanceira
CREATE TABLE MovimentacaoFinanceira (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT NOT NULL,
    data_emissao DATE,
    forma_pagamento VARCHAR(50),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

-- Tabela Caixa
CREATE TABLE Caixa (
    id BIGINT PRIMARY KEY,
    data_abertura DATETIME NOT NULL,
    data_fechamento DATETIME,
    saldo_inicial DOUBLE,
    saldo_final DOUBLE
);

-- Tabela Venda
CREATE TABLE Venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT,
    valor_total DOUBLE,
    desconto DOUBLE,
    valor_pago DOUBLE,
    data_venda DATE,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

-- Tabela ItensVenda
CREATE TABLE ItensVenda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venda BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INT,
    FOREIGN KEY (id_venda) REFERENCES Venda(id),
    FOREIGN KEY (id_produto) REFERENCES Produto(id)
);

-- Tabela UnidadeMedida
CREATE TABLE UnidadeMedida (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(20) NOT NULL,
    abreviacao VARCHAR(2) NOT NULL
);

CREATE TABLE TipoInsumo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

-- Tabela Insumo
CREATE TABLE Insumo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    quantidadeEstoqueIn DOUBLE,
    id_unidade_medida BIGINT NOT NULL,
    custo_unitario DOUBLE,
    data_validade DATE,
    id_tipo_insumo BIGINT NOT NULL,
    FOREIGN KEY (id_unidade_medida) REFERENCES UnidadeMedida(id),
    FOREIGN KEY (id_tipo_insumo) REFERENCES TipoInsumo(id)
);

-- Tabela ItemFichaProduto
CREATE TABLE ItemFichaProduto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_insumo BIGINT NOT NULL,
    quantidade DOUBLE,
    id_unidade_medida BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES Produto(id),
    FOREIGN KEY (id_insumo) REFERENCES Insumo(id),
    FOREIGN KEY (id_unidade_medida) REFERENCES UnidadeMedida(id)
);

-- Tabela NotaFiscal
CREATE TABLE NotaFiscal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_empresa VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20)
);

-- Tabela TiposImposto
CREATE TABLE TiposImposto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

