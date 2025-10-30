DROP DATABASE IF EXISTS marmitaria;
CREATE DATABASE marmitaria;
USE marmitaria;

-- Tabela clientes
CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    saldo DOUBLE,
    limite_credito BOOLEAN
);

-- Tabela tipo_produto
CREATE TABLE tipo_produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

-- Tabela produto
CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_produto BIGINT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    quantidade_estoque DOUBLE,
    estoque_minimo DOUBLE,
    preco_venda DOUBLE,
    FOREIGN KEY (id_tipo_produto) REFERENCES tipo_produto(id)
);

-- Tabela movimentacao_financeira
CREATE TABLE movimentacao_financeira (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT NOT NULL,
    data_emissao DATE,
    forma_pagamento VARCHAR(50),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

-- Tabela caixa
CREATE TABLE caixa (
    id BIGINT PRIMARY KEY,
    data_abertura DATETIME NOT NULL,
    data_fechamento DATETIME,
    saldo_inicial DOUBLE,
    saldo_final DOUBLE
);

-- Tabela venda
CREATE TABLE venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT,
    valor_total DOUBLE,
    desconto DOUBLE,
    valor_pago DOUBLE,
    data_venda DATE,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

-- Tabela itens_venda
CREATE TABLE itens_venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venda BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INT,
    FOREIGN KEY (id_venda) REFERENCES venda(id),
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);

-- Tabela unidade_medida
CREATE TABLE unidade_medida (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(20) NOT NULL,
    abreviacao VARCHAR(2) NOT NULL
);

-- Tabela tipo_insumo
CREATE TABLE tipo_insumo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

-- Tabela insumo
CREATE TABLE insumo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    quantidade_estoque_in DOUBLE,
    id_unidade_medida BIGINT NOT NULL,
    custo_unitario DOUBLE,
    data_validade DATE,
    id_tipo_insumo BIGINT NOT NULL,
    FOREIGN KEY (id_unidade_medida) REFERENCES unidade_medida(id),
    FOREIGN KEY (id_tipo_insumo) REFERENCES tipo_insumo(id)
);

-- Tabela item_ficha_produto
CREATE TABLE item_ficha_produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_insumo BIGINT NOT NULL,
    quantidade DOUBLE,
    id_unidade_medida BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_insumo) REFERENCES insumo(id),
    FOREIGN KEY (id_unidade_medida) REFERENCES unidade_medida(id)
);

-- Tabela nota_fiscal
CREATE TABLE nota_fiscal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_empresa VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20)
);

-- Tabela tipos_imposto
CREATE TABLE tipos_imposto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
<<<<<<< HEAD
);
=======
);
>>>>>>> ec90ce1a4462fd5e1dc71ef3fca9cdd0b31076a2
