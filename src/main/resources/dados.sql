-- ===========================================
-- POPULAR BANCO DE DADOS MARMITARIA
-- ===========================================

USE marmitaria;

-- ------------------------------
-- CLIENTES
-- ------------------------------
INSERT INTO clientes (nome, endereco, telefone, saldo, limite_credito) VALUES
('João Silva', 'Rua A, 123', '11999990001', 0, TRUE),
('Maria Souza', 'Rua B, 45', '11999990002', 20, FALSE),
('Carlos Pereira', 'Av Central, 888', '11999990003', -10, TRUE),
('Fernanda Lima', 'Rua C, 222', '11999990004', 50, TRUE),
('Roberto Dias', 'Rua D, 120', '11999990005', 0, FALSE),
('Juliana Alves', 'Rua E, 500', '11999990006', 5, TRUE),
('Diego Rocha', 'Rua F, 11', '11999990007', -23, TRUE),
('Lucas Santos', 'Rua G, 202', '11999990008', 0, FALSE),
('Aline Costa', 'Rua H, 404', '11999990009', 120, TRUE),
('Paula Mendes', 'Av Brasil, 1010', '11999990010', 10, TRUE);

-- ------------------------------
-- TIPO PRODUTO
-- ------------------------------
INSERT INTO tipo_produto (tipo) VALUES
('Marmita'),
('Bebida'),
('Sobremesa'),
('Adicional'),
('Combo');

-- ------------------------------
-- PRODUTOS
-- ------------------------------
INSERT INTO produto (id_tipo_produto, nome, quantidade_estoque, estoque_minimo, preco_venda) VALUES
(1, 'Marmita Média', 120, 20, 18.00),
(1, 'Marmita Grande', 90, 15, 22.00),
(1, 'Marmita Fit', 70, 10, 20.00),
(2, 'Refrigerante Lata', 150, 30, 6.00),
(2, 'Suco Natural', 80, 20, 8.00),
(3, 'Pudim', 40, 5, 5.00),
(3, 'Mousse de Maracujá', 35, 5, 5.00),
(4, 'Adicional de Carne', 50, 10, 7.00),
(4, 'Adicional de Frango', 60, 10, 6.00),
(5, 'Combo Marmita + Lata', 100, 10, 23.00),
(5, 'Combo Marmita Grande + Suco', 70, 10, 28.00),
(3, 'Bolo Gelado', 50, 10, 4.00),
(2, 'Água Mineral 500ml', 200, 30, 3.00),
(4, 'Adicional de Ovo', 120, 20, 2.50),
(4, 'Adicional Bacon', 80, 10, 4.50);


-- ------------------------------
-- UNIDADES DE MEDIDA
-- ------------------------------
INSERT INTO unidade_medida (descricao, abreviacao) VALUES
('Quilograma', 'KG'),
('Grama', 'G'),
('Litro', 'L'),
('Mililitro', 'ML'),
('Unidade', 'UN'),
('Pacote', 'PC');


-- ------------------------------
-- TIPOS DE INSUMOS
-- ------------------------------
INSERT INTO tipo_insumo (tipo) VALUES
('Carnes'),
('Grãos'),
('Temperos'),
('Bebidas'),
('Laticínios'),
('Embalagens');

-- ------------------------------
-- INSUMOS
-- ------------------------------
INSERT INTO insumo (nome, quantidade_estoque_in, id_unidade_medida, custo_unitario, data_validade, id_tipo_insumo) VALUES
('Arroz', 50, 1, 4.00, '2025-12-30', 2),
('Feijão', 40, 1, 6.50, '2025-12-15', 2),
('Carne Bovina', 30, 1, 25.00, '2025-02-10', 1),
('Frango Desfiado', 25, 1, 18.00, '2025-02-20', 1),
('Ovo', 300, 5, 0.70, '2025-01-20', 1),
('Bacon', 20, 1, 30.00, '2025-02-10', 1),
('Batata', 60, 1, 5.00, '2025-01-15', 2),
('Sal', 10, 1, 1.00, '2027-01-10', 3),
('Açúcar', 20, 1, 2.50, '2027-05-20', 3),
('Leite Condensado', 25, 5, 4.80, '2025-04-18', 5),
('Creme de Leite', 25, 5, 4.00, '2025-04-10', 5),
('Refrigerante Lata', 200, 5, 3.00, '2026-01-01', 4),
('Água Mineral', 300, 5, 1.00, '2026-03-01', 4),
('Suco Concentrado', 50, 3, 2.50, '2025-07-01', 4),
('Embalagem Marmita Média', 200, 6, 0.80, '2027-10-01', 6),
('Embalagem Marmita Grande', 200, 6, 1.00, '2027-10-01', 6),
('Copo Descartável', 400, 6, 0.20, '2027-12-01', 6),
('Farinha de Trigo', 40, 1, 3.00, '2025-10-10', 2),
('Maracujá', 15, 1, 8.00, '2025-02-01', 2),
('Chocolate em Pó', 10, 1, 9.00, '2025-04-01', 5);


-- ------------------------------
-- FICHAS TÉCNICAS
-- (Definindo os insumos de cada produto)
-- ------------------------------
INSERT INTO item_ficha_produto (id_insumo, quantidade, id_unidade_medida, id_produto) VALUES
-- Marmita Média
(1, 0.25, 1, 1),
(2, 0.25, 1, 1),
(3, 0.20, 1, 1),
(15, 1, 6, 1),

-- Marmita Grande
(1, 0.35, 1, 2),
(2, 0.35, 1, 2),
(3, 0.30, 1, 2),
(16, 1, 6, 2),

-- Marmita Fit
(1, 0.20, 1, 3),
(4, 0.25, 1, 3),
(7, 0.10, 1, 3),
(15, 1, 6, 3),

-- Refrigerante Lata
(12, 1, 5, 4),

-- Suco Natural
(14, 0.30, 3, 5),

-- Pudim
(10, 0.20, 5, 6),
(18, 0.05, 1, 6),
(9, 0.05, 1, 6),

-- Mousse Maracujá
(11, 0.20, 5, 7),
(19, 0.05, 1, 7),

-- Adicional Carne
(3, 0.15, 1, 8),

-- Adicional Frango
(4, 0.15, 1, 9),

-- Combo Marmita + Lata
(1, 0.25, 1, 10),
(2, 0.25, 1, 10),
(3, 0.20, 1, 10),
(12, 1, 5, 10),
(15, 1, 6, 10),

-- Combo Marmita Grande + Suco
(1, 0.35, 1, 11),
(2, 0.35, 1, 11),
(3, 0.30, 1, 11),
(14, 0.30, 3, 11),
(16, 1, 6, 11),

-- Bolo Gelado
(18, 0.10, 1, 12),
(10, 0.10, 5, 12),

-- Água Mineral
(13, 1, 5, 13),

-- Adicional Ovo
(5, 1, 5, 14),

-- Adicional Bacon
(6, 0.10, 1, 15);


-- ------------------------------
-- VENDAS
-- ------------------------------

INSERT INTO venda (id_cliente, valor_total, desconto, valor_pago, data_venda) VALUES
(1, 18, 0, 18, '2025-01-03'),
(2, 26, 2, 24, '2025-01-03'),
(3, 22, 0, 22, '2025-01-04'),
(4, 23, 0, 23, '2025-01-04'),
(5, 6, 0, 6, '2025-01-05'),
(6, 5, 0, 5, '2025-01-05'),
(7, 20, 0, 20, '2025-01-06'),
(8, 28, 0, 28, '2025-01-06'),
(1, 23, 0, 23, '2025-01-07'),
(2, 18, 0, 18, '2025-01-07'),
(3, 22, 2, 20, '2025-01-08'),
(4, 6, 0, 6, '2025-01-08'),
(5, 23, 0, 23, '2025-01-09'),
(6, 28, 0, 28, '2025-01-09'),
(7, 4, 0, 4, '2025-01-10'),
(8, 5, 0, 5, '2025-01-10'),
(9, 20, 0, 20, '2025-01-11'),
(10, 6, 0, 6, '2025-01-11'),
(3, 18, 0, 18, '2025-01-12'),
(1, 28, 0, 28, '2025-01-12');

-- ------------------------------
-- ITENS DA VENDA
-- ------------------------------
INSERT INTO itens_venda (id_venda, id_produto, quantidade) VALUES
(1, 1, 1),
(2, 2, 1),
(2, 5, 1),
(3, 2, 1),
(4, 10, 1),
(5, 4, 1),
(6, 6, 1),
(7, 3, 1),
(8, 11, 1),
(9, 10, 1),
(10, 1, 1),
(11, 2, 1),
(12, 4, 1),
(13, 10, 1),
(14, 11, 1),
(15, 12, 1),
(16, 6, 1),
(17, 3, 1),
(18, 4, 1),
(19, 1, 1),
(20, 11, 1);



-- ------------------------------
-- MOVIMENTAÇÃO FINANCEIRA
-- ------------------------------
INSERT INTO movimentacao_financeira (id_cliente, data_emissao, forma_pagamento) VALUES
(1, '2025-01-03', 'PIX'),
(2, '2025-01-03', 'Dinheiro'),
(3, '2025-01-04', 'Débito'),
(4, '2025-01-04', 'Crédito'),
(5, '2025-01-05', 'Dinheiro'),
(6, '2025-01-05', 'PIX'),
(7, '2025-01-06', 'PIX'),
(8, '2025-01-06', 'Dinheiro'),
(9, '2025-01-11', 'Crédito'),
(10, '2025-01-11', 'Dinheiro');

-- ------------------------------
-- CAIXA
-- ------------------------------
INSERT INTO caixa (data_abertura, data_fechamento, saldo_inicial, saldo_final, status) VALUES
('2025-01-03 08:00:00', '2025-01-03 18:00:00', 100, 300, 'Fechado'),
('2025-01-04 08:00:00', '2025-01-04 18:00:00', 300, 520, 'Fechado'),
('2025-01-05 08:00:00', NULL, 520, NULL, 'Aberto');


-- ------------------------------
-- MOVIMENTAÇÃO DE CAIXA
-- ------------------------------
INSERT INTO movimentacao_caixa (caixa_id, tipo, descricao, valor, data_hora) VALUES
(1, 'ENTRADA', 'Venda 1', 18, '2025-01-03 12:00:00'),
(1, 'ENTRADA', 'Venda 2', 24, '2025-01-03 12:10:00'),
(1, 'SAÍDA', 'Compra embalagens', 50, '2025-01-03 15:00:00'),
(1, 'ENTRADA', 'Venda 3', 22, '2025-01-03 16:00:00'),

(2, 'ENTRADA', 'Venda 4', 23, '2025-01-04 10:00:00'),
(2, 'ENTRADA', 'Venda 5', 6, '2025-01-04 10:30:00'),
(2, 'ENTRADA', 'Venda 6', 5, '2025-01-04 12:00:00'),
(2, 'SAÍDA', 'Compra carne', 120, '2025-01-04 14:00:00'),
(2, 'ENTRADA', 'Venda 7', 20, '2025-01-04 15:00:00'),

(3, 'ENTRADA', 'Venda 8', 28, '2025-01-05 11:00:00'),
(3, 'ENTRADA', 'Venda 9', 23, '2025-01-05 13:00:00'),
(3, 'SAÍDA', 'Pagamento entrega', 30, '2025-01-05 15:00:00');


-- ------------------------------
-- NOTA FISCAL
-- ------------------------------
INSERT INTO nota_fiscal (nome_empresa, cnpj) VALUES
('Marmitaria Sabor Caseiro', '12.345.678/0001-90'),
('Delícias da Casa', '98.765.432/0001-22'),
('Bom Prato Express', '45.678.901/0001-55'),
('Sabor & Arte', '33.222.111/0001-10'),
('Comida Boa Ltda', '77.555.444/0001-60');


-- ------------------------------
-- TIPOS DE IMPOSTO
-- ------------------------------

INSERT INTO tipos_imposto (tipo) VALUES
('ICMS'),
('ISS'),
('PIS'),
('COFINS'),
('IPI'),
('CSLL');



-- ------------------------------
-- MOVIMENTAÇÃO FINANCEIRA
-- ------------------------------
INSERT INTO movimentacao_financeira (id_cliente, data_emissao, forma_pagamento) VALUES
(1, '2025-10-25', 'Dinheiro'),
(2, '2025-10-25', 'Pix'),
(3, '2025-10-26', 'Cartão');

