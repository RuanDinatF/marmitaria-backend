-- ===========================================
-- POPULAR BANCO DE DADOS MARMITARIA
-- ===========================================

USE marmitaria;

-- ------------------------------
-- CLIENTES
-- ------------------------------
INSERT INTO clientes (nome, endereco, telefone, saldo, limite_credito) VALUES
('João Silva', 'Rua das Flores, 123', '11999990001', 50.00, TRUE),
('Maria Souza', 'Av. Paulista, 456', '11999990002', 120.00, FALSE),
('Carlos Pereira', 'Rua dos Limoeiros, 789', '11999990003', 0.00, TRUE),
('Ana Lima', 'Rua da Paz, 22', '11999990004', 200.00, TRUE);

-- ------------------------------
-- TIPO PRODUTO
-- ------------------------------
INSERT INTO tipo_produto (tipo) VALUES
('Marmita'),
('Bebida'),
('Sobremesa');

-- ------------------------------
-- UNIDADE DE MEDIDA
-- ------------------------------
INSERT INTO unidade_medida (descricao, abreviacao) VALUES
('Grama', 'g'),
('Litro', 'L'),
('Unidade', 'un'),
('Mililitro', 'ml');

-- ------------------------------
-- TIPO INSUMO
-- ------------------------------
INSERT INTO tipo_insumo (tipo) VALUES
('Proteína'),
('Carboidrato'),
('Verdura/Legume'),
('Embalagem'),
('Bebida Base'),
('Sobremesa Base');

-- ------------------------------
-- INSUMOS
-- ------------------------------
INSERT INTO insumo (nome, quantidade_estoque_in, id_unidade_medida, custo_unitario, data_validade, id_tipo_insumo) VALUES
('Frango desfiado', 10000, 1, 0.025, '2025-12-30', 1),
('Arroz branco', 20000, 1, 0.005, '2026-01-15', 2),
('Feijão carioca', 15000, 1, 0.006, '2026-02-10', 2),
('Alface', 5000, 1, 0.008, '2025-11-05', 3),
('Caixa de isopor', 1000, 3, 1.00, '2027-01-01', 4),
('Suco natural', 100, 2, 3.50, '2025-11-10', 5),
('Pudim de leite', 50, 3, 4.00, '2025-11-12', 6);

-- ------------------------------
-- PRODUTOS
-- ------------------------------
INSERT INTO produto (id_tipo_produto, nome, quantidade_estoque, estoque_minimo, preco_venda) VALUES
(1, 'Marmita de Frango', 50, 10, 15.00),
(1, 'Marmita Vegetariana', 40, 10, 14.00),
(2, 'Suco de Laranja 500ml', 100, 20, 6.00),
(3, 'Pudim de Leite 120g', 30, 10, 5.50);

-- ------------------------------
-- ITEM FICHA PRODUTO
-- (Definindo os insumos de cada produto)
-- ------------------------------
INSERT INTO item_ficha_produto (id_insumo, quantidade, id_unidade_medida, id_produto) VALUES
-- Marmita de Frango
(1, 200, 1, 1), -- Frango
(2, 150, 1, 1), -- Arroz
(3, 100, 1, 1), -- Feijão
(4, 50, 1, 1),  -- Alface
(5, 1, 3, 1),   -- Embalagem
-- Marmita Vegetariana
(2, 200, 1, 2),
(3, 100, 1, 2),
(4, 80, 1, 2),
(5, 1, 3, 2),
-- Suco
(6, 1, 2, 3),
-- Pudim
(7, 1, 3, 4);

-- ------------------------------
-- NOTA FISCAL
-- ------------------------------
INSERT INTO nota_fiscal (nome_empresa, cnpj) VALUES
('Marmitaria Sabor Caseiro LTDA', '12.345.678/0001-99'),
('Distribuidora de Insumos Gourmet ME', '98.765.432/0001-55');

-- ------------------------------
-- TIPOS DE IMPOSTO
-- ------------------------------
INSERT INTO tipos_imposto (tipo) VALUES
('ICMS'),
('PIS'),
('COFINS');

-- ------------------------------
-- CAIXA
-- ------------------------------
INSERT INTO caixa (id, data_abertura, data_fechamento, saldo_inicial, saldo_final) VALUES
(1, '2025-10-25 08:00:00', '2025-10-25 18:00:00', 500.00, 950.00),
(2, '2025-10-26 08:00:00', NULL, 600.00, NULL);

-- ------------------------------
-- MOVIMENTAÇÃO FINANCEIRA
-- ------------------------------
INSERT INTO movimentacao_financeira (id_cliente, data_emissao, forma_pagamento) VALUES
(1, '2025-10-25', 'Dinheiro'),
(2, '2025-10-25', 'Pix'),
(3, '2025-10-26', 'Cartão');

-- ------------------------------
-- VENDAS
-- ------------------------------
INSERT INTO venda (id_cliente, valor_total, desconto, valor_pago, data_venda) VALUES
(1, 30.00, 0.00, 30.00, '2025-10-25'),
(2, 20.00, 2.00, 18.00, '2025-10-25'),
(3, 15.00, 0.00, 15.00, '2025-10-26');

-- ------------------------------
-- ITENS VENDA
-- ------------------------------
INSERT INTO itens_venda (id_venda, id_produto, quantidade) VALUES
(1, 1, 2), -- 2 marmitas de frango
(2, 2, 1), -- 1 marmita vegetariana
(2, 4, 1), -- 1 pudim
(3, 3, 1); -- 1 suco
